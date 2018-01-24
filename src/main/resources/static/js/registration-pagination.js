$('.form-group').pagination({
    items: 10,
    itemOnPage: 6,
    currentPage: 1,
    cssStyle: '',
    prevText: '<span aria-hidden="true">&laquo;</span>',
    nextText: '<span aria-hidden="true">&raquo;</span>',
    onInit: function goToPage (pageNumber) {
        currentPage = pageNumber;
    },
    onPageClick: $(".pagination-page").click(function(){
        goToPage($(this).text());
    })
});