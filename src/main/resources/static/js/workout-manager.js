(function () {
    "use strict";
    // $(document).ready(function () {

        // const blogPost = {title: 'Ajax Requests', body: 'Are a fun way to use JS!'};
        const url = "https://wger.de/api/v2/workout/";
        const options = {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Token a68a4eaf22f78199a0abdeb073fd368b9d6ee74e"
            },
            // body: JSON.stringify(blogPost),
        };
        const myPromise = fetch(url, options);
            myPromise.then(() => console.log("resolved!"));
            myPromise.catch(() => console.log("Rejected!"));
        // const myPromise = fetch('https://api.github.com/users');
        // myPromise.then(response => console.log(response));
        // myPromise.catch(error => console.error(error));
// });
})();

// # In the request header
// "Authorization": "Token a68a4eaf22f78199a0abdeb073fd368b9d6ee74e"
//
//
// # Example with curl
//     curl -X GET https://wger.de/api/v2/workout/ \
//     -H 'Authorization: Token 1234567890abcde...'