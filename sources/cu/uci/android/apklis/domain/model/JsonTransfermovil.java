package cu.uci.android.apklis.domain.model;

/* loaded from: classes.dex */
public class JsonTransfermovil {
    private String id_transaccion;
    private double importe;
    private String moneda;
    private int numero_proveedor;
    private int version;

    public JsonTransfermovil(String str, double d, String str2, int i, int i2) {
        this.id_transaccion = str;
        this.importe = d;
        this.moneda = str2;
        this.numero_proveedor = i;
        this.version = i2;
    }

    public String getId_transaccion() {
        return this.id_transaccion;
    }

    public double getImporte() {
        return this.importe;
    }

    public String getMoneda() {
        return this.moneda;
    }

    public int getNumero_proveedor() {
        return this.numero_proveedor;
    }

    public int getVersion() {
        return this.version;
    }

    public void setId_transaccion(String str) {
        this.id_transaccion = str;
    }

    public void setImporte(double d) {
        this.importe = d;
    }

    public void setMoneda(String str) {
        this.moneda = str;
    }

    public void setNumero_proveedor(int i) {
        this.numero_proveedor = i;
    }

    public void setVersion(int i) {
        this.version = i;
    }
}
