(function () {
    "use strict";
    const url = "https://wger.de/api/v2/exercise/";
    const options = {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Token a68a4eaf22f78199a0abdeb073fd368b9d6ee74e"
        }
        // body: '{"key": "value"}'
        // body: JSON.stringify("),
    };
    const myPromise = fetch(url, options);
    myPromise
        .then((response) => response.json())
        //.json is a method that is returning another promise, which will now return a json object.
        .then((response) => {
            var html = "<ul>";
            response.results.forEach(function (exercise) {
                html += `<li> ${exercise.name}</li>`;
                html += `<li> ${exercise.description}</li>`;
            });
            html += "</ul>";
            document.getElementById('exercises').innerHTML = html;
            // $('#exercises').html(html);
        });
    myPromise.catch(() => console.log("Rejected!"));
})();
