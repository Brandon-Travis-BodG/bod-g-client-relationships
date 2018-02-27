(function () {
    "use strict";
    // const url = "https://wger.de/api/v2/exerciseimage/?limit=400";
    const url = "https://wger.de/api/v2/exercise/?language=2&limit=500";
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
            let html = "<ul>";
            response.results.forEach(function (exercise) {
                // html += `<li> ${exercise.name}</li>`;
                // html += `<li> ${exercise.description}</li>`;
                html += `<li><img id="image" src="${exercise.image}"</img></li>`;
            });
            html += "</ul>";
            document.getElementById('exercises').innerHTML = html;
            // $('#exercises').html(html);
            console.log(response);
        });
    myPromise.catch(() => console.log("Rejected!"));
})();


// const url = "https://wger.de/api/v2/exercise/?language=2&limit=500";
// const options = {
//     method: "GET",
//     headers: {
//         "Content-Type": "application/json",
//         "Authorization": "Token a68a4eaf22f78199a0abdeb073fd368b9d6ee74e"
//     },
//     // body: '{"key": "value"}'
//     // body: JSON.stringify("),
// };
// const myPromise = fetch(url, options);
// myPromise
//     .then((response) => response.json())
//     //.json is a method that is returning another promise, which will now return a key
//     .then((response) => console.log(response));
// myPromise.catch(() => console.log("Rejected!"));






// (function () {
//     "use strict";
//
//     "https://wger.de/api/v2/exerciseimage/?exercise=" + exercise.id
//
//
//     // const url = "https://wger.de/api/v2/exerciseimage/";
//
//
//
// //     const url = "https://wger.de/api/v2/exercise/?language=2&limit=500";
// //     const options = {
// //         method: "GET",
// //         headers: {
// //             "Content-Type": "application/json",
// //             "Authorization": "Token a68a4eaf22f78199a0abdeb073fd368b9d6ee74e"
// //         }
// //         // body: '{"key": "value"}'
// //         // body: JSON.stringify("),
// //     };
// //     const myPromise = fetch(url, options);
// //     myPromise
// //         .then((response) => response.json())
// //         //.json is a method that is returning another promise, which will now return a json object.
// //         .then((exercises) => {
// //
// //
// //             let html = "<ul>";
// //             let urls = [];
// //             exercises.results.forEach(function (exercise) {
// //                 // urls.push("https://wger.de/api/v2/exerciseimage/?exercise=" + exercise.id);
// //                 //html += `<li> ${exercise.name}</li>`;
// //                 //html += `<li> ${exercise.description}</li>`;
// //
// //                 fetch("https://wger.de/api/v2/exerciseimage/?exercise=" + exercise.id)
// //                     .then(response => response.json())
// //                     .then(image => {
// //                         html += `<li><img id="image" src="${exercise.image}"</img></li>`;
// //                     })
// //                 ;
// //
// //             });
// //             html += "</ul>";
// //             document.getElementById('exercises').innerHTML = html;
// //             // $('#exercises').html(html);
// //         });
// //     myPromise.catch(() => console.log("Rejected!"));
// // })();
// //
// //
// // // const url = "https://wger.de/api/v2/exercise/?language=2&limit=500";
// // // const options = {
// // //     method: "GET",
// // //     headers: {
// // //         "Content-Type": "application/json",
// // //         "Authorization": "Token a68a4eaf22f78199a0abdeb073fd368b9d6ee74e"
// // //     },
// // //     // body: '{"key": "value"}'
// // //     // body: JSON.stringify("),
// // // };
// // // const myPromise = fetch(url, options);
// // // myPromise
// // //     .then((response) => response.json())
// // //     //.json is a method that is returning another promise, which will now return a key
// // //     .then((response) => console.log(response));
// // // myPromise.catch(() => console.log("Rejected!"));
