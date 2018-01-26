(function () {

$( document ).ready(function() {

var listElement = $(".page");
var perPage = 6;
var numItems = listElement.length;
var numPages = Math.ceil(numItems/perPage);
console.log(numItems);
console.log(numPages);

$(".pager").attr("currentPage", 0);
//.attr is creating and setting an attribute called currentpage with a value of 0.


$(".pager").addClass("active");

listElement.css("display", "none");
listElement.slice(0, perPage).css("display", "block");

$("#previous").click(function(){
    var currPage = $(".pager").attr("currentPage");
    var goToPage = parseInt(currPage) - 1;
    if(goToPage >= 0){
        goTo(goToPage);
    }
    console.log(currPage);
    console.log(goToPage);
});

$("#next").click(function(){
    var currPage = $(".pager").attr("currentPage");
    var goToPage = parseInt(currPage) + 1;
    if(goToPage < numPages){
        goTo(goToPage);
    }
    console.log(currPage);
    console.log(goToPage);
});
//.attr or .data either sets or gets the value in the html
//page resets the currentPage to whatever is passed in depending on if next or previous is passed in.

//Coming from the DOM it comes in as a string so must convert it as a number so it doesn"t concatenate.

//.attr is getting the value of the current page attribute and parseInt turns the value of the current page from a string to a number.

function goTo(page){
    var startAt = page * perPage;
    //page 1 would be index 0 * 6, and there is a slice so only 0 to 5 will be take.

    var endOn = startAt + perPage;
    //page  starts on index 6 and go to index 11 cause of the slice.

    listElement.css("display","none");
    listElement.slice(startAt, endOn).css("display","block");
    $(".pager").attr("currentPage", page);
}
});
})();
//page resets the currentPage to whatever is passed in depending on if next or previous is passed in.
