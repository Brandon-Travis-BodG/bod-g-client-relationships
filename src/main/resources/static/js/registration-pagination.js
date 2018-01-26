var listElement = $(".page");
var perPage = 6;
var numItems = listElement.length;
var numPages = Math.ceil(numItems/perPage);
console.log(numItems);
console.log(numPages);