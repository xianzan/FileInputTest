package entity;

/**
 * post请求实体类
 */
public class Process {
    private String error_handling;
    private String folder_path_type;
    private String folder_path_value;
    private String entry_name;
    private String ext_id;
    private String entry_file_path;
    private String put_mode;

    public Process() {
    }

    public Process(String error_handling, String folder_path_type, String folder_path_value, String entry_name, String ext_id, String entry_file_path, String put_mode) {
        this.error_handling = error_handling;
        this.folder_path_type = folder_path_type;
        this.folder_path_value = folder_path_value;
        this.entry_name = entry_name;
        this.ext_id = ext_id;
        this.entry_file_path = entry_file_path;
        this.put_mode = put_mode;
    }

    public String getError_handling() {
        return error_handling;
    }

    public void setError_handling(String error_handling) {
        this.error_handling = error_handling;
    }

    public String getFolder_path_type() {
        return folder_path_type;
    }

    public void setFolder_path_type(String folder_path_type) {
        this.folder_path_type = folder_path_type;
    }

    public String getFolder_path_value() {
        return folder_path_value;
    }

    public void setFolder_path_value(String folder_path_value) {
        this.folder_path_value = folder_path_value;
    }

    public String getEntry_name() {
        return entry_name;
    }

    public void setEntry_name(String entry_name) {
        this.entry_name = entry_name;
    }

    public String getExt_id() {
        return ext_id;
    }

    public void setExt_id(String ext_id) {
        this.ext_id = ext_id;
    }

    public String getEntry_file_path() {
        return entry_file_path;
    }

    public void setEntry_file_path(String entry_file_path) {
        this.entry_file_path = entry_file_path;
    }

    public String getPut_mode() {
        return put_mode;
    }

    public void setPut_mode(String put_mode) {
        this.put_mode = put_mode;
    }

    @Override
    public String toString() {
        return "Process{" +
                "error_handling='" + error_handling + '\'' +
                ", folder_path_type='" + folder_path_type + '\'' +
                ", folder_path_value='" + folder_path_value + '\'' +
                ", entry_name='" + entry_name + '\'' +
                ", ext_id='" + ext_id + '\'' +
                ", entry_file_path='" + entry_file_path + '\'' +
                ", put_mode='" + put_mode + '\'' +
                '}';
    }
}
