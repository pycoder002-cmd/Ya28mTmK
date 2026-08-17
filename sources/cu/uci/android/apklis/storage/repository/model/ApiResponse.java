package cu.uci.android.apklis.storage.repository.model;

/* loaded from: classes.dex */
public class ApiResponse<T> {
    private Integer count;
    private String next;
    private String previous;
    private T[] results;

    public ApiResponse() {
    }

    public ApiResponse(Integer num, String str, String str2, T[] tArr) {
        this.count = num;
        this.next = str;
        this.previous = str2;
        this.results = tArr;
    }

    public Integer getCount() {
        return this.count;
    }

    public String getNext() {
        return this.next;
    }

    public String getPrevious() {
        return this.previous;
    }

    public T[] getResults() {
        return this.results;
    }

    public void setCount(Integer num) {
        this.count = num;
    }

    public void setNext(String str) {
        this.next = str;
    }

    public void setPrevious(String str) {
        this.previous = str;
    }

    public void setResults(T[] tArr) {
        this.results = tArr;
    }
}
