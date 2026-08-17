package com.startapp;

import com.startapp.networkTest.controller.LocationController;
import com.startapp.networkTest.enums.AnonymizationLevel;
import com.startapp.networkTest.enums.BatteryStatusUploadConstraints;
import com.startapp.networkTest.enums.CtCriteriaTypes;
import com.startapp.networkTest.enums.LtrCriteriaTypes;
import com.startapp.networkTest.enums.TrafficDetectionMode;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class q {
    private boolean APPUSAGE_MEASURE_ALL_MPA;
    private boolean BANDWDITH_TEST_MANAGER_GET_IMEI_IMSI;
    private boolean CLEAR_AUS_LOCATION_INFO;
    private boolean CLEAR_CT_LOCATION_INFO;
    private boolean CLEAR_LTR_LOCATION_INFO;
    private boolean CLEAR_MSG_LOCATION_INFO;
    private boolean CLEAR_NF_LOCATION_INFO;
    private boolean CLEAR_NTR_LOCATION_INFO;
    private boolean CLEAR_RSS_LOCATION_INFO;
    private boolean CLEAR_VC_LOCATION_INFO;
    private boolean CLEAR_WPT_LOCATION_INFO;
    private CtCriteriaTypes CONNECTIVITY_TEST_CRITERIA;
    private String[] CONNECTIVITY_TEST_HOSTNAME_ARRAY;
    private long CORE_EXPIRATION_TIMESTAMP;
    private boolean CT_COLLECT_CELLINFO;
    private long DATABASE_ENTRIES_MAX_AGE_DAYS;
    private boolean FOREGROUND_TEST_CT_ENABLED;
    private long FOREGROUND_TEST_CT_SCHEDULE_INTERVAL;
    private boolean FOREGROUND_TEST_NIR_ENABLED;
    private boolean GEOIP_MOBILE_ENABLED;
    private String GEOIP_URL;
    private long GUID_MAX_AGE;
    private LtrCriteriaTypes LATENCY_TEST_CRITERIA;
    private String[] LATENCY_TEST_HOSTNAME_ARRAY;
    private LocationController.ProviderMode LTR_LOCATIONPROVIDER;
    private AnonymizationLevel MESSAGINGMANAGER_PHONENUMBER_RECORD_TYPE;
    private boolean MESSAGING_SERVICE_ENABLED;
    private boolean NIR_COLLECT_CELLINFO;
    private int NIR_COLLECT_CELLINFO_THRESHOLD;
    private boolean NTP_SYNC_ENABLED;
    private String P3ST_CONTROL_SERVER;
    private int P3ST_CUSTOM_TCP_PORT;
    private boolean QOE_MANAGER_ENABLED;
    private boolean SEND_REGISTRATION_TIMESTAMP_ENABLED;
    private AnonymizationLevel SIMINFO_ICCID_RECORDTYPE;
    private AnonymizationLevel SIMINFO_IMSI_RECORDTYPE;
    private boolean START_SERVICES_IN_FOREGROUND;
    private boolean STATSMANAGER_ENABLED;
    private long STATSMANAGER_ENTRIES_MAX_AGE_DAYS;
    private boolean STATSMANAGER_LEGACY_ENABLED;
    private int[] STATSMANAGER_SIGNAL_STRENGTH_MAPPING_2G;
    private int[] STATSMANAGER_SIGNAL_STRENGTH_MAPPING_3G;
    private int[] STATSMANAGER_SIGNAL_STRENGTH_MAPPING_4G;
    private int[] STATSMANAGER_SIGNAL_STRENGTH_MAPPING_5G;
    private boolean STATSMANAGER_USE_NTR_FOR_RATSHARE_AND_SIGNALSTRENGTH;
    private boolean TRAFFIC_ANALYZER_ENABLED;
    private boolean TRAFFIC_ANALYZER_MPT_ENABLED;
    private boolean VC_COLLECT_CELLINFO;
    private LocationController.ProviderMode VOICEMANAGER_LOCATIONPROVIDER;
    private LocationController.ProviderMode VOICEMANAGER_LOCATIONPROVIDER_DROPPEDWINDOW;
    private AnonymizationLevel VOICEMANAGER_PHONENUMBER_RECORD_TYPE;
    private boolean VOICEMANAGER_SAVE_DROPPEDWINDOW_MPVS;
    private boolean VOICEMANAGER_USE_READ_CALL_LOG_PERMISSION;
    private boolean VOICE_SERVICE_ENABLED;
    private String VOWIFI_TEST_EPDG_LATENCY_IP;
    private String VOWIFI_TEST_EPDG_SERVER_URL;
    private boolean VOWIFI_TEST_MANAGER_ENABLED;
    private String VOWIFI_TEST_PORT_TEST_SERVER_URL;
    private AnonymizationLevel WIFIINFO_BSSID_RECORDTYPE;
    private AnonymizationLevel WIFIINFO_SSID_RECORDTYPE;
    private AnonymizationLevel WIFISCAN_BSSID_RECORDTYPE;
    private AnonymizationLevel WIFISCAN_SSID_RECORDTYPE;
    private boolean WIFI_SCAN_ENABLED;
    private long WIFI_SCAN_MINIMUM_INTERVAL;
    private String PROJECT_ID = "0";
    private String CAMPAIGN_ID = "Campaign001";
    private String UPLOAD_DUS_URL = "https://awsdus.api.p3insight.de/isupload/upload_check_lumen.php";
    private String UPLOAD_TICKET_URL = "https://ul.api.c0nnectthed0ts.com/ul/v3/";
    private boolean UPLOAD_ENABLED_IN_ROAMING = true;
    private boolean UPLOAD_ENABLED_IN_ROAMING_WIFI = true;
    private long UPLOAD_TIMESPAN_BETWEEN_UPLOADS = 57600000;
    private long UPLOAD_TIMESPAN_BETWEEN_UPLOADS_WIFI = 14400000;
    private long UPLOAD_TIMESPAN_BETWEEN_EXPORTS = 57600000;
    private BatteryStatusUploadConstraints UPLOAD_BATTERY_STATUS_CONSTRAINT = BatteryStatusUploadConstraints.Always;
    private boolean UPLOAD_INFORMATION_ENABLED = false;
    private boolean CONNECTIVITY_TEST_ENABLED = false;
    private boolean CONNECTIVITY_KEEPALIVE_ENABLED = false;
    private String CONNECTIVITY_TEST_HOSTNAME = "";
    private String CONNECTIVITY_TEST_FILENAME = "";
    private String CONNECTIVITY_TEST_IP = "";
    private boolean CONNECTIVITY_TEST_ENABLED_IN_ROAMING = true;
    private long CONNECTIVITY_KEEPALIVE_INTERVAL = 86400000;
    private long CONNECTIVITY_TEST_INTERVAL = 900000;
    private float CONNECTIVITY_TEST_MIN_BATTERY_LEVEL = 15.0f;
    private String CONNECTIVITY_TEST_TRUSTSTORE_URL = "https://d2to8y50b3n6dq.cloudfront.net/truststores/[PROJECTID]/truststore.zip";
    private boolean CONNECTIVITY_TEST_VERIFY_TRUSTSTORE_SIGNATURE = false;
    private long CONNECTIVITY_TEST_TRUSTSTORE_UPDATE_INTERVAL = 86400000;
    private long CONNECTIVITY_TEST_CDNCONFIG_UPDATE_INTERVAL = 86400000;
    private String CONNECTIVITY_TEST_CDNCONFIG_URL = "https://d2to8y50b3n6dq.cloudfront.net/truststores/[PROJECTID]/cdnconfig.zip";
    private boolean CONNECTIVITY_TEST_VERIFY_CDNCONFIG_SIGNATURE = false;
    private boolean CONNECTIVITY_TEST_ALLOW_IN_IDLE = false;
    private boolean APPUSAGE_SERVICE_ENABLED = false;
    private boolean APPUSAGE_MANAGER_INSTALLED_APP_SNAPSHOT_ENABLED = false;
    private boolean APPUSAGE_BROWSER_SESSION_TRACKING_ENABLED = false;
    private TrafficDetectionMode APPUSAGE_TRAFFIC_DETECTION_MODE = TrafficDetectionMode.Auto;
    private boolean INSTALLED_APP_SNAPSHOT_PERMISSIONS_ENABLED = false;
    private boolean INSTALLED_APP_SNAPSHOT_SIGNATURES_ENABLED = false;
    private boolean COVERAGE_MAPPER_SERVICE_ENABLED = false;
    private LocationController.ProviderMode COVERAGE_MAPPER_SERVICE_TRIGGER_PROVIDER_MODE = LocationController.ProviderMode.Gps;
    private long COVERAGE_MAPPER_SERVICE_OUT_OF_SERVICE_MEASUREMENT_DUARTION = 0;
    private long COVERAGE_MAPPER_SERVICE_CELL_ID_CHANGE_MEASUREMENT_DUARTION = 0;

    public q() {
        AnonymizationLevel anonymizationLevel = AnonymizationLevel.None;
        this.VOICEMANAGER_PHONENUMBER_RECORD_TYPE = anonymizationLevel;
        this.VOICEMANAGER_LOCATIONPROVIDER = LocationController.ProviderMode.GpsAndNetwork;
        LocationController.ProviderMode providerMode = LocationController.ProviderMode.Passive;
        this.VOICEMANAGER_LOCATIONPROVIDER_DROPPEDWINDOW = providerMode;
        this.VOICEMANAGER_SAVE_DROPPEDWINDOW_MPVS = false;
        this.VOICEMANAGER_USE_READ_CALL_LOG_PERMISSION = true;
        this.LTR_LOCATIONPROVIDER = providerMode;
        this.MESSAGINGMANAGER_PHONENUMBER_RECORD_TYPE = anonymizationLevel;
        this.VOICE_SERVICE_ENABLED = false;
        this.MESSAGING_SERVICE_ENABLED = false;
        this.VOWIFI_TEST_MANAGER_ENABLED = false;
        this.GUID_MAX_AGE = 15552000000L;
        this.STATSMANAGER_ENABLED = false;
        this.STATSMANAGER_LEGACY_ENABLED = false;
        this.STATSMANAGER_USE_NTR_FOR_RATSHARE_AND_SIGNALSTRENGTH = false;
        this.STATSMANAGER_SIGNAL_STRENGTH_MAPPING_2G = new int[]{-57, -79, -89, -99};
        this.STATSMANAGER_SIGNAL_STRENGTH_MAPPING_3G = new int[]{-65, -85, -95, -101};
        this.STATSMANAGER_SIGNAL_STRENGTH_MAPPING_4G = new int[]{-79, -92, -112, -124};
        this.STATSMANAGER_SIGNAL_STRENGTH_MAPPING_5G = new int[]{-95, -105, -115, -120};
        this.STATSMANAGER_ENTRIES_MAX_AGE_DAYS = 30L;
        this.DATABASE_ENTRIES_MAX_AGE_DAYS = 30L;
        this.TRAFFIC_ANALYZER_ENABLED = false;
        this.TRAFFIC_ANALYZER_MPT_ENABLED = false;
        this.QOE_MANAGER_ENABLED = false;
        this.SEND_REGISTRATION_TIMESTAMP_ENABLED = false;
        this.GEOIP_URL = "https://geoip.api.c0nnectthed0ts.com/geoip/";
        this.GEOIP_MOBILE_ENABLED = false;
        this.P3ST_CONTROL_SERVER = "control.st.p3insight.de";
        this.P3ST_CUSTOM_TCP_PORT = 20000;
        this.START_SERVICES_IN_FOREGROUND = false;
        this.BANDWDITH_TEST_MANAGER_GET_IMEI_IMSI = false;
        this.APPUSAGE_MEASURE_ALL_MPA = false;
        this.CORE_EXPIRATION_TIMESTAMP = -1L;
        AnonymizationLevel anonymizationLevel2 = AnonymizationLevel.Full;
        this.WIFIINFO_SSID_RECORDTYPE = anonymizationLevel2;
        this.WIFIINFO_BSSID_RECORDTYPE = anonymizationLevel2;
        this.WIFISCAN_SSID_RECORDTYPE = anonymizationLevel;
        this.WIFISCAN_BSSID_RECORDTYPE = anonymizationLevel;
        AnonymizationLevel anonymizationLevel3 = AnonymizationLevel.Anonymized;
        this.SIMINFO_ICCID_RECORDTYPE = anonymizationLevel3;
        this.SIMINFO_IMSI_RECORDTYPE = anonymizationLevel3;
        this.NTP_SYNC_ENABLED = true;
        this.VOWIFI_TEST_PORT_TEST_SERVER_URL = "";
        this.VOWIFI_TEST_EPDG_SERVER_URL = "";
        this.VOWIFI_TEST_EPDG_LATENCY_IP = "";
        this.NIR_COLLECT_CELLINFO = false;
        this.NIR_COLLECT_CELLINFO_THRESHOLD = 1;
        this.VC_COLLECT_CELLINFO = false;
        this.CT_COLLECT_CELLINFO = false;
        this.CLEAR_AUS_LOCATION_INFO = false;
        this.CLEAR_CT_LOCATION_INFO = false;
        this.CLEAR_LTR_LOCATION_INFO = false;
        this.CLEAR_MSG_LOCATION_INFO = false;
        this.CLEAR_NF_LOCATION_INFO = false;
        this.CLEAR_NTR_LOCATION_INFO = false;
        this.CLEAR_RSS_LOCATION_INFO = false;
        this.CLEAR_WPT_LOCATION_INFO = false;
        this.CLEAR_VC_LOCATION_INFO = false;
        this.CONNECTIVITY_TEST_HOSTNAME_ARRAY = new String[0];
        this.CONNECTIVITY_TEST_CRITERIA = CtCriteriaTypes.Random;
        this.LATENCY_TEST_HOSTNAME_ARRAY = new String[0];
        this.LATENCY_TEST_CRITERIA = LtrCriteriaTypes.CTItem;
        this.WIFI_SCAN_MINIMUM_INTERVAL = 600000L;
        this.WIFI_SCAN_ENABLED = false;
        this.FOREGROUND_TEST_NIR_ENABLED = true;
        this.FOREGROUND_TEST_CT_ENABLED = true;
        this.FOREGROUND_TEST_CT_SCHEDULE_INTERVAL = 300000L;
    }

    public boolean APPUSAGE_BROWSER_SESSION_TRACKING_ENABLED() {
        return this.APPUSAGE_BROWSER_SESSION_TRACKING_ENABLED;
    }

    public boolean APPUSAGE_MANAGER_INSTALLED_APP_SNAPSHOT_ENABLED() {
        return this.APPUSAGE_MANAGER_INSTALLED_APP_SNAPSHOT_ENABLED;
    }

    public boolean APPUSAGE_MEASURE_ALL_MPA() {
        return this.APPUSAGE_MEASURE_ALL_MPA;
    }

    public boolean APPUSAGE_SERVICE_ENABLED() {
        return this.APPUSAGE_SERVICE_ENABLED;
    }

    public TrafficDetectionMode APPUSAGE_TRAFFIC_DETECTION_MODE() {
        return this.APPUSAGE_TRAFFIC_DETECTION_MODE;
    }

    public boolean BANDWDITH_TEST_MANAGER_GET_IMEI_IMSI() {
        return this.BANDWDITH_TEST_MANAGER_GET_IMEI_IMSI;
    }

    public String CAMPAIGN_ID() {
        return this.CAMPAIGN_ID;
    }

    public boolean CLEAR_AUS_LOCATION_INFO() {
        return this.CLEAR_AUS_LOCATION_INFO;
    }

    public boolean CLEAR_CT_LOCATION_INFO() {
        return this.CLEAR_CT_LOCATION_INFO;
    }

    public boolean CLEAR_LTR_LOCATION_INFO() {
        return this.CLEAR_LTR_LOCATION_INFO;
    }

    public boolean CLEAR_MSG_LOCATION_INFO() {
        return this.CLEAR_MSG_LOCATION_INFO;
    }

    public boolean CLEAR_NF_LOCATION_INFO() {
        return this.CLEAR_NF_LOCATION_INFO;
    }

    public boolean CLEAR_NTR_LOCATION_INFO() {
        return this.CLEAR_NTR_LOCATION_INFO;
    }

    public boolean CLEAR_RSS_LOCATION_INFO() {
        return this.CLEAR_RSS_LOCATION_INFO;
    }

    public boolean CLEAR_VC_LOCATION_INFO() {
        return this.CLEAR_VC_LOCATION_INFO;
    }

    public boolean CLEAR_WPT_LOCATION_INFO() {
        return this.CLEAR_WPT_LOCATION_INFO;
    }

    public boolean CONNECTIVITY_KEEPALIVE_ENABLED() {
        return this.CONNECTIVITY_KEEPALIVE_ENABLED;
    }

    public long CONNECTIVITY_KEEPALIVE_INTERVAL() {
        return this.CONNECTIVITY_KEEPALIVE_INTERVAL;
    }

    public boolean CONNECTIVITY_TEST_ALLOW_IN_IDLE() {
        return this.CONNECTIVITY_TEST_ALLOW_IN_IDLE;
    }

    public long CONNECTIVITY_TEST_CDNCONFIG_UPDATE_INTERVAL() {
        return this.CONNECTIVITY_TEST_CDNCONFIG_UPDATE_INTERVAL;
    }

    public String CONNECTIVITY_TEST_CDNCONFIG_URL() {
        return this.CONNECTIVITY_TEST_CDNCONFIG_URL;
    }

    public CtCriteriaTypes CONNECTIVITY_TEST_CRITERIA() {
        return this.CONNECTIVITY_TEST_CRITERIA;
    }

    public boolean CONNECTIVITY_TEST_ENABLED() {
        return this.CONNECTIVITY_TEST_ENABLED;
    }

    public boolean CONNECTIVITY_TEST_ENABLED_IN_ROAMING() {
        return this.CONNECTIVITY_TEST_ENABLED_IN_ROAMING;
    }

    public String CONNECTIVITY_TEST_FILENAME() {
        return this.CONNECTIVITY_TEST_FILENAME;
    }

    public String CONNECTIVITY_TEST_HOSTNAME() {
        return this.CONNECTIVITY_TEST_HOSTNAME;
    }

    public String[] CONNECTIVITY_TEST_HOSTNAME_ARRAY() {
        return this.CONNECTIVITY_TEST_HOSTNAME_ARRAY;
    }

    public long CONNECTIVITY_TEST_INTERVAL() {
        return this.CONNECTIVITY_TEST_INTERVAL;
    }

    public String CONNECTIVITY_TEST_IP() {
        return this.CONNECTIVITY_TEST_IP;
    }

    public float CONNECTIVITY_TEST_MIN_BATTERY_LEVEL() {
        return this.CONNECTIVITY_TEST_MIN_BATTERY_LEVEL;
    }

    public long CONNECTIVITY_TEST_TRUSTSTORE_UPDATE_INTERVAL() {
        return this.CONNECTIVITY_TEST_TRUSTSTORE_UPDATE_INTERVAL;
    }

    public String CONNECTIVITY_TEST_TRUSTSTORE_URL() {
        return this.CONNECTIVITY_TEST_TRUSTSTORE_URL;
    }

    public boolean CONNECTIVITY_TEST_VERIFY_CDNCONFIG_SIGNATURE() {
        return this.CONNECTIVITY_TEST_VERIFY_CDNCONFIG_SIGNATURE;
    }

    public boolean CONNECTIVITY_TEST_VERIFY_TRUSTSTORE_SIGNATURE() {
        return this.CONNECTIVITY_TEST_VERIFY_TRUSTSTORE_SIGNATURE;
    }

    public long CORE_EXPIRATION_TIMESTAMP() {
        return this.CORE_EXPIRATION_TIMESTAMP;
    }

    public long COVERAGE_MAPPER_SERVICE_CELL_ID_CHANGE_MEASUREMENT_DUARTION() {
        return this.COVERAGE_MAPPER_SERVICE_CELL_ID_CHANGE_MEASUREMENT_DUARTION;
    }

    public boolean COVERAGE_MAPPER_SERVICE_ENABLED() {
        return this.COVERAGE_MAPPER_SERVICE_ENABLED;
    }

    public long COVERAGE_MAPPER_SERVICE_OUT_OF_SERVICE_MEASUREMENT_DUARTION() {
        return this.COVERAGE_MAPPER_SERVICE_OUT_OF_SERVICE_MEASUREMENT_DUARTION;
    }

    public LocationController.ProviderMode COVERAGE_MAPPER_SERVICE_TRIGGER_PROVIDER_MODE() {
        return this.COVERAGE_MAPPER_SERVICE_TRIGGER_PROVIDER_MODE;
    }

    public boolean CT_COLLECT_CELLINFO() {
        return this.CT_COLLECT_CELLINFO;
    }

    public long DATABASE_ENTRIES_MAX_AGE_DAYS() {
        return this.DATABASE_ENTRIES_MAX_AGE_DAYS;
    }

    public boolean FOREGROUND_TEST_CT_ENABLED() {
        return this.FOREGROUND_TEST_CT_ENABLED;
    }

    public long FOREGROUND_TEST_CT_SCHEDULE_INTERVAL() {
        return this.FOREGROUND_TEST_CT_SCHEDULE_INTERVAL;
    }

    public boolean FOREGROUND_TEST_NIR_ENABLED() {
        return this.FOREGROUND_TEST_NIR_ENABLED;
    }

    public boolean GEOIP_MOBILE_ENABLED() {
        return this.GEOIP_MOBILE_ENABLED;
    }

    public String GEOIP_URL() {
        return this.GEOIP_URL;
    }

    public long GUID_MAX_AGE() {
        return this.GUID_MAX_AGE;
    }

    public boolean INSTALLED_APP_SNAPSHOT_PERMISSIONS_ENABLED() {
        return this.INSTALLED_APP_SNAPSHOT_PERMISSIONS_ENABLED;
    }

    public boolean INSTALLED_APP_SNAPSHOT_SIGNATURES_ENABLED() {
        return this.INSTALLED_APP_SNAPSHOT_SIGNATURES_ENABLED;
    }

    public LtrCriteriaTypes LATENCY_TEST_CRITERIA() {
        return this.LATENCY_TEST_CRITERIA;
    }

    public String[] LATENCY_TEST_HOSTNAME_ARRAY() {
        return this.LATENCY_TEST_HOSTNAME_ARRAY;
    }

    public LocationController.ProviderMode LTR_LOCATIONPROVIDER() {
        return this.LTR_LOCATIONPROVIDER;
    }

    public AnonymizationLevel MESSAGINGMANAGER_PHONENUMBER_RECORD_TYPE() {
        return this.MESSAGINGMANAGER_PHONENUMBER_RECORD_TYPE;
    }

    public boolean MESSAGING_SERVICE_ENABLED() {
        return this.MESSAGING_SERVICE_ENABLED;
    }

    public boolean NIR_COLLECT_CELLINFO() {
        return this.NIR_COLLECT_CELLINFO;
    }

    public int NIR_COLLECT_CELLINFO_THRESHOLD() {
        return this.NIR_COLLECT_CELLINFO_THRESHOLD;
    }

    public boolean NTP_SYNC_ENABLED() {
        return this.NTP_SYNC_ENABLED;
    }

    public String P3ST_CONTROL_SERVER() {
        return this.P3ST_CONTROL_SERVER;
    }

    public int P3ST_CUSTOM_TCP_PORT() {
        return this.P3ST_CUSTOM_TCP_PORT;
    }

    public String PROJECT_ID() {
        return this.PROJECT_ID;
    }

    public boolean QOE_MANAGER_ENABLED() {
        return this.QOE_MANAGER_ENABLED;
    }

    public boolean SEND_REGISTRATION_TIMESTAMP_ENABLED() {
        return this.SEND_REGISTRATION_TIMESTAMP_ENABLED;
    }

    public AnonymizationLevel SIMINFO_ICCID_RECORDTYPE() {
        return this.SIMINFO_ICCID_RECORDTYPE;
    }

    public AnonymizationLevel SIMINFO_IMSI_RECORDTYPE() {
        return this.SIMINFO_IMSI_RECORDTYPE;
    }

    public boolean START_SERVICES_IN_FOREGROUND() {
        return this.START_SERVICES_IN_FOREGROUND;
    }

    public boolean STATSMANAGER_ENABLED() {
        return this.STATSMANAGER_ENABLED;
    }

    public long STATSMANAGER_ENTRIES_MAX_AGE_DAYS() {
        return this.STATSMANAGER_ENTRIES_MAX_AGE_DAYS;
    }

    public boolean STATSMANAGER_LEGACY_ENABLED() {
        return this.STATSMANAGER_LEGACY_ENABLED;
    }

    public int[] STATSMANAGER_SIGNAL_STRENGTH_MAPPING_2G() {
        return this.STATSMANAGER_SIGNAL_STRENGTH_MAPPING_2G;
    }

    public int[] STATSMANAGER_SIGNAL_STRENGTH_MAPPING_3G() {
        return this.STATSMANAGER_SIGNAL_STRENGTH_MAPPING_3G;
    }

    public int[] STATSMANAGER_SIGNAL_STRENGTH_MAPPING_4G() {
        return this.STATSMANAGER_SIGNAL_STRENGTH_MAPPING_4G;
    }

    public int[] STATSMANAGER_SIGNAL_STRENGTH_MAPPING_5G() {
        return this.STATSMANAGER_SIGNAL_STRENGTH_MAPPING_5G;
    }

    public boolean STATSMANAGER_USE_NTR_FOR_RATSHARE_AND_SIGNALSTRENGTH() {
        return this.STATSMANAGER_USE_NTR_FOR_RATSHARE_AND_SIGNALSTRENGTH;
    }

    public boolean TRAFFIC_ANALYZER_ENABLED() {
        return this.TRAFFIC_ANALYZER_ENABLED;
    }

    public boolean TRAFFIC_ANALYZER_MPT_ENABLED() {
        return this.TRAFFIC_ANALYZER_MPT_ENABLED;
    }

    public BatteryStatusUploadConstraints UPLOAD_BATTERY_STATUS_CONSTRAINT() {
        return this.UPLOAD_BATTERY_STATUS_CONSTRAINT;
    }

    public String UPLOAD_DUS_URL() {
        return this.UPLOAD_DUS_URL;
    }

    public boolean UPLOAD_ENABLED_IN_ROAMING() {
        return this.UPLOAD_ENABLED_IN_ROAMING;
    }

    public boolean UPLOAD_ENABLED_IN_ROAMING_WIFI() {
        return this.UPLOAD_ENABLED_IN_ROAMING_WIFI;
    }

    public boolean UPLOAD_INFORMATION_ENABLED() {
        return this.UPLOAD_INFORMATION_ENABLED;
    }

    public String UPLOAD_TICKET_URL() {
        return this.UPLOAD_TICKET_URL;
    }

    public long UPLOAD_TIMESPAN_BETWEEN_EXPORTS() {
        return this.UPLOAD_TIMESPAN_BETWEEN_EXPORTS;
    }

    public long UPLOAD_TIMESPAN_BETWEEN_UPLOADS() {
        return this.UPLOAD_TIMESPAN_BETWEEN_UPLOADS;
    }

    public long UPLOAD_TIMESPAN_BETWEEN_UPLOADS_WIFI() {
        return this.UPLOAD_TIMESPAN_BETWEEN_UPLOADS_WIFI;
    }

    public boolean VC_COLLECT_CELLINFO() {
        return this.VC_COLLECT_CELLINFO;
    }

    public LocationController.ProviderMode VOICEMANAGER_LOCATIONPROVIDER() {
        return this.VOICEMANAGER_LOCATIONPROVIDER;
    }

    public LocationController.ProviderMode VOICEMANAGER_LOCATIONPROVIDER_DROPPEDWINDOW() {
        return this.VOICEMANAGER_LOCATIONPROVIDER_DROPPEDWINDOW;
    }

    public AnonymizationLevel VOICEMANAGER_PHONENUMBER_RECORD_TYPE() {
        return this.VOICEMANAGER_PHONENUMBER_RECORD_TYPE;
    }

    public boolean VOICEMANAGER_SAVE_DROPPEDWINDOW_MPVS() {
        return this.VOICEMANAGER_SAVE_DROPPEDWINDOW_MPVS;
    }

    public boolean VOICEMANAGER_USE_READ_CALL_LOG_PERMISSION() {
        return this.VOICEMANAGER_USE_READ_CALL_LOG_PERMISSION;
    }

    public boolean VOICE_SERVICE_ENABLED() {
        return this.VOICE_SERVICE_ENABLED;
    }

    public String VOWIFI_TEST_EPDG_LATENCY_IP() {
        return this.VOWIFI_TEST_EPDG_LATENCY_IP;
    }

    public String VOWIFI_TEST_EPDG_SERVER_URL() {
        return this.VOWIFI_TEST_EPDG_SERVER_URL;
    }

    public boolean VOWIFI_TEST_MANAGER_ENABLED() {
        return this.VOWIFI_TEST_MANAGER_ENABLED;
    }

    public String VOWIFI_TEST_PORT_TEST_SERVER_URL() {
        return this.VOWIFI_TEST_PORT_TEST_SERVER_URL;
    }

    public AnonymizationLevel WIFIINFO_BSSID_RECORDTYPE() {
        return this.WIFIINFO_BSSID_RECORDTYPE;
    }

    public AnonymizationLevel WIFIINFO_SSID_RECORDTYPE() {
        return this.WIFIINFO_SSID_RECORDTYPE;
    }

    public AnonymizationLevel WIFISCAN_BSSID_RECORDTYPE() {
        return this.WIFISCAN_BSSID_RECORDTYPE;
    }

    public AnonymizationLevel WIFISCAN_SSID_RECORDTYPE() {
        return this.WIFISCAN_SSID_RECORDTYPE;
    }

    public boolean WIFI_SCAN_ENABLED() {
        return this.WIFI_SCAN_ENABLED;
    }

    public long WIFI_SCAN_MINIMUM_INTERVAL() {
        return this.WIFI_SCAN_MINIMUM_INTERVAL;
    }
}
