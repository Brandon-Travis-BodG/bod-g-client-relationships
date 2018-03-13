(function () {
    "use strict";

    // "https://wger.de/api/v2/exerciseimage/?exercise=" + exercise.id


    // const url = "https://wger.de/api/v2/exerciseimage/";


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
        .then((exercises) => {


            let html = "<ul>";
            let urls = [];
            // let obj = [{
            //     ex1: {
            //         name,
            //         descrip,
            //         id
            //         pic: [urls, urls,url]
            //     },
            //for loop { obj[i].pic // push url into obj[i].pic

            // }];

            exercises.results.forEach(function (exercise) {
                if (exercise.category == 9 || exercise.category == 14) {
                    // return exercise.name && exercise.description
                    // }
                    // else (exercise.category !== 10)
                    // {
                    //     return;
                    // }
                    // }
                    // urls.push("https://wger.de/api/v2/exerciseimage/?exercise=" + exercise.id);
                    // put each element inside obj
                    html += `<h3 id="exerciseName"> ${exercise.name}</h3>`;
                    html += `<li> ${exercise.description}</li>`;
                    html += `<li id="exercise-${exercise.id}"></li>`;
                    // var string;
                    // for loop
                    // ex[i].name, ex[i].description, ex[i].id
                    // after loop --> string = <li>ex1.name</li>....
                    fetch("https://wger.de/api/v2/exerciseimage/?exercise=" + exercise.id)
                    // put each element inside object in the correct exercise
                    // obj.ex1.picture =
                        .then(response => response.json())
                        .then(images => {
                            console.log(images);
                            if (images.results.length === 0) return;
                            // let imageHtml = `<li><img id="image" src="${image.results[0].image}"</img></li>`;
                            // $(`#exercise-${exercise.id}`).html(imageHtml);
                            let imageHtml = "<li>";
                            images.results.forEach(function (image) {
                                imageHtml += `<img id="image" src="${image.image}"</img></li>`;
                            });
                            imageHtml += "<li>";
                            $(`#exercise-${exercise.id}`).html(imageHtml);
                        });
                }
            });
            html += "</ul>";
            // $("#back").click(function (event) {
            //     event.preventDefault();
            //     $("#exercises").show(exercises.category == 9 || exercise.category == 14);
            // exercise.category == 9 || exercise.category == 14;
            // });
            document.getElementById('leg-exercises').innerHTML = html;
            // $('#exercises').html(html);
        });
    myPromise.catch(() => console.log("Rejected!"));

})();