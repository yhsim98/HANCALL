package domain;

public class PageMaker {
    private final Long pageSize;
    private Long totalPage;
    private Long startPage;
    private Long endPage;
    private Long page;
    private Long pageScale;

    public PageMaker() {
        this.pageSize = 10L;
        this.startPage = 1L;
        this.pageScale = 5L;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }

    public Long getStartPage() {
        return startPage;
    }

    public void setStartPage(Long startPage) {
        this.startPage = startPage;
    }

    public Long getEndPage() {
        return endPage;
    }

    public void setEndPage(Long endPage) {
        this.endPage = endPage;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }
}
