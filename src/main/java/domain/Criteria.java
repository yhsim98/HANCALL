package domain;

public class Criteria {

    // 현재 페이지 번호
    private int page;
    // 한 페이지당 보여줄 게시글의 개수
    private int perPageNum;

    // 특정 페이지의 게시글 시작 번호
    public int getPageStart(){
        return (this.page - 1) * perPageNum;
    }

    public Criteria() {
        this.page = 1;
        this.perPageNum = 10;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        if(page <= 0){
            this.page = 1;
        } else{
            this.page = page;
        }
    }

    public int getPerPageNum() {
        return perPageNum;
    }

    public void setPerPageNum(int perPageNum) {
        if (perPageNum <= 0 || perPageNum > 100) {
            this.perPageNum = 10;
        } else {
            this.perPageNum = perPageNum;
        }
    }
}
