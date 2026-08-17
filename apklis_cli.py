import requests
import json
import sys

BASE_URL = "http://api.apklis.cu"
WEB_URL = "https://www.apklis.cu"

class ApklisAdvancedClient:
    def __init__(self):
        self.access_token = None
        self.sdk_version = 26
        self.ignore_sdk = False
        self.user_agent = "Dalvik/2.1.0 (Linux; U; Android 6.0.1; A315-58 Build/MMB29M)"

    def _get_headers(self):
        headers = {
            "Content-Type": "application/json",
            "Accept": "application/json",
            "User-Agent": self.user_agent
        }
        if self.access_token:
            headers["Authorization"] = self.access_token
        return headers

    def authenticate(self):
        print("\n--- 1. INICIAR SESIÓN ---")
        username = input("Usuario / Correo: ").strip()
        password = input("Contraseña: ").strip()

        endpoint = f"{BASE_URL}/users/login"
        payload = {"username": username, "password": password}

        try:
            print("[*] Autenticando en el servidor...")
            response = requests.post(endpoint, json=payload, headers=self._get_headers(), timeout=15)
            if response.status_code == 200:
                data = response.json()
                token = data.get("access_token") or data.get("token") or data.get("key") or data.get("id")
                if token:
                    self.access_token = f"Bearer {token}" if not str(token).startswith("Bearer") else token
                    print("[+] ¡Autenticación exitosa! Token guardado en la sesión.")
                else:
                    print("[+] Respuesta de login:", json.dumps(data, indent=2))
            else:
                print(f"[!] Error de autenticación ({response.status_code}): {response.text}")
        except Exception as e:
            print(f"[!] Error de conexión: {e}")

    def print_app_visual(self, app):
        """Muestra la aplicación en un formato visual mejorado."""
        name = app.get("name", "N/A")
        pkg = app.get("package_name", "N/A")
        rating = app.get("rating", 0.0)
        price = app.get("price", 0.0)
        updated = app.get("updated", "N/A")

        last_release = app.get("last_release") or {}
        icon_url = last_release.get("icon", "No disponible")
        version = last_release.get("version_name", "N/A")

        app_web_link = f"{WEB_URL}/application/{pkg}"

        print(f"\n🚀 {name} ({pkg})")
        print(f"   📅 Actualizada: {updated} | 📦 Versión: {version}")
        print(f"   ⭐ Calificación: {rating} | 💰 Precio: {price} CUP")
        print(f"   🖼️  Icono: {icon_url}")
        print(f"   🔗 Enlace Web: {app_web_link}")
        print("-" * 50)

    def get_apps(self, ordering=None):
        print(f"\n--- {'LISTAR APLICACIONES' if not ordering else 'APPS MÁS RECIENTES'} ---")
        try:
            limit = int(input("Límite de resultados (ej. 10): ") or "10")
            offset = int(input("Desplazamiento / Offset (ej. 0): ") or "0")
            query = None
            if not ordering:
                query = input("Término de búsqueda (dejar en blanco para listar todas): ").strip()
                query = query if query else None
        except ValueError:
            print("[!] Valores numéricos inválidos.")
            return

        endpoint = f"{BASE_URL}/v1/application/"
        params = {"limit": limit, "offset": offset}
        if query:
            params["name__icontains"] = query
        if ordering:
            params["ordering"] = ordering
        if not self.ignore_sdk:
            params["releases__version_sdk__lte"] = self.sdk_version

        try:
            print(f"[*] Consultando {endpoint} ...")
            response = requests.get(endpoint, headers=self._get_headers(), params=params, timeout=15)
            if response.status_code == 200:
                data = response.json()
                results = data.get("results", [])
                if not results:
                    print("[i] No se encontraron aplicaciones.")
                else:
                    for app in results:
                        self.print_app_visual(app)
                    print(f"\n[+] Se mostraron {len(results)} aplicaciones.")
            else:
                print(f"[!] Error del servidor ({response.status_code}): {response.text}")
        except Exception as e:
            print(f"[!] Error de conexión: {e}")

    def get_app_details(self):
        print("\n--- 3. DETALLES DE UNA APLICACIÓN Y ÚLTIMO RELEASE ---")
        package_name = input("Introduce el nombre de paquete (ej. cu.uci.android.apklis): ").strip()
        if not package_name:
            print("[!] El nombre de paquete no puede estar vacío.")
            return

        endpoint = f"{BASE_URL}/applications/{package_name}/lastRelease"
        try:
            print(f"[*] Consultando información en {endpoint} ...")
            response = requests.get(endpoint, headers=self._get_headers(), timeout=15)
            if response.status_code == 200:
                data = response.json()
                print(json.dumps(data, indent=2, ensure_ascii=False))
            elif response.status_code == 404:
                print(f"[!] Aplicación '{package_name}' no encontrada.")
            else:
                print(f"[!] Error del servidor ({response.status_code}): {response.text}")
        except Exception as e:
            print(f"[!] Error de conexión: {e}")

    def get_categories(self):
        print("\n--- 4. LISTAR CATEGORÍAS ---")
        endpoint = f"{BASE_URL}/v1/category/"
        try:
            print(f"[*] Obteniendo categorías de {endpoint} ...")
            response = requests.get(endpoint, headers=self._get_headers(), timeout=15)
            if response.status_code == 200:
                data = response.json()
                print(json.dumps(data, indent=2, ensure_ascii=False))
            else:
                print(f"[!] Error del servidor ({response.status_code}): {response.text}")
        except Exception as e:
            print(f"[!] Error de conexión: {e}")

    def buy_application(self):
        print("\n--- 5. SOLICITAR ORDEN DE COMPRA DE APP ---")
        if not self.access_token:
            print("[!] Advertencia: No estás autenticado. Las compras requieren iniciar sesión primero (Opción 1).")

        app_id = input("Introduce el ID numérico de la aplicación a comprar: ").strip()
        if not app_id:
            print("[!] El ID no puede estar vacío.")
            return

        endpoint = f"{BASE_URL}/v1/application/{app_id}/buy/"
        try:
            print(f"[*] Solicitando orden de compra en {endpoint}...")
            response = requests.get(endpoint, headers=self._get_headers(), allow_redirects=False, timeout=15)

            print(f"[+] Código de Estado HTTP: {response.status_code}")
            if response.status_code in [200, 201, 302]:
                print(f"[+] Cuerpo de Respuesta:\n{response.text}")
                print("\n[i] (Nota: Si se integra con Transfermóvil, la pasarela devolvería los datos del pago).")
            else:
                print(f"[!] Error: {response.status_code}")
        except Exception as e:
            print(f"[!] Error de conexión: {e}")


def main_menu():
    client = ApklisAdvancedClient()

    while True:
        print("\n========================================")
        print("   APKLIS API - ADVANCED CONSOLE CLI")
        print("========================================")
        print(f"Estado de Sesión: {'[AUTENTICADO]' if client.access_token else '[ANÓNIMO]'}")
        print("1. Iniciar sesión (Obtener Token)")
        print("2. Listar / Buscar aplicaciones (Normal)")
        print("3. Ver apps más recientes (Por fecha)")
        print("4. Ver detalles de una app y último release")
        print("5. Listar categorías")
        print("6. Comprar / Solicitar orden de app")
        print("7. Salir")

        choice = input("\nSelecciona una opción (1-7): ").strip()

        if choice == "1":
            client.authenticate()
        elif choice == "2":
            client.get_apps()
        elif choice == "3":
            client.get_apps(ordering="-updated")
        elif choice == "4":
            client.get_app_details()
        elif choice == "5":
            client.get_categories()
        elif choice == "6":
            client.buy_application()
        elif choice == "7":
            print("\n¡Hasta luego!")
            sys.exit(0)
        else:
            print("[!] Opción no válida. Elige entre 1 y 7.")

if __name__ == "__main__":
    try:
        main_menu()
    except KeyboardInterrupt:
        print("\n\nOperación cancelada por el usuario. Saliendo...")
        sys.exit(0)
