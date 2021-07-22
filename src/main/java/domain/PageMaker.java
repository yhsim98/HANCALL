package domain;

public class PageMaker {

    private Criteria cri;

    // 총 게시글 수
    private int totalCount;
    // 화면에 보여질 첫 페이지 번호
    private int startPage;
    // 화면에 보여질 마지막 페이지 번호
    private int endPage;
    // 화면에 prev 존재 여부
    private boolean prev;
    // 화면에 next 존재 여부
    private boolean next;
    // 한 화면에 보여줄 페이지 개수
    private int displayPageNum = 5;

    public PageMaker(Criteria cri, int totalBoardCount) {
        this.cri = cri;
        this.totalCount = totalBoardCount;
        calcData();
    }

    public PageMaker() {}

    private void calcData(){
        this.endPage = (int) (Math.ceil(cri.getPage() / (double) displayPageNum) * displayPageNum);
        this.startPage = (endPage - displayPageNum) + 1;

        if(startPage <= 0) startPage = 1;

        // 끝 페이지가 실제 게시물 개수에 따른 끝 페이지보다 많을 경우
        int tempEndPage = (int)(Math.ceil(totalCount / (double) cri.getPerPageNum()));
        if(endPage > tempEndPage){
            endPage = tempEndPage;
        }

        prev = startPage == 1 ? false : true;
        next = endPage * cri.getPerPageNum() < totalCount ? true : false;
    }

    public Criteria getCri() {
        return cri;
    }

    public void setCri(Criteria cri) {
        this.cri = cri;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        calcData();
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public boolean isPrev() {
        return prev;
    }

    public void setPrev(boolean prev) {
        this.prev = prev;
    }

    public boolean isNext() {
        return next;
    }

    public void setNext(boolean next) {
        this.next = next;
    }

    public int getDisplayPageNum() {
        return displayPageNum;
    }
}
