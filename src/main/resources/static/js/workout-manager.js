(function () {
    "use strict";

    function getParameterByName(name, url) {
        if (!url) url = window.location.href;
        name = name.replace(/[\[\]]/g, "\\$&");
        const regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
            results = regex.exec(url);
        if (!results) return null;
        if (!results[2]) return '';
        return decodeURIComponent(results[2].replace(/\+/g, " "));
    }

    //changing the query string into an object

    const category = getParameterByName('category');

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


            let html = "<div id='workouts'>";
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
                if (exercise.category === Number(category)) {
                    if (exercise.name !== "") {
                        // return exercise.name && exercise.description
                        // }
                        // else (exercise.category !== 10)
                        // {
                        //     return;
                        // }
                        // }
                        // urls.push("https://wger.de/api/v2/exerciseimage/?exercise=" + exercise.id);
                        // put each element inside obj
                        html += `<div class="exercise" data-title="${exercise.name.toUpperCase()}">`;
                        //adding an attribute that surrounds the below block to be able to show or hide the whole block
                        html += `<h3 id="exerciseName"> ${exercise.name}</h3>`;
                        html += `<p> ${exercise.description}</p>`;
                        html += `<p id="exercise-${exercise.id}"></p>`;
                        html += `</div>`;
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
                }
            });
            html += "</ul>";
            // $("#arms").click(function (event) {
            //     event.preventDefault();
            //     $("#exercises").show(exercises.category == 8);
            //     // exercise.category == 8;
            // });
            document.getElementById('exercises').innerHTML = html;
            // $('#exercises').html(html);
        });
    myPromise.catch(() => console.log("Rejected!"));

    function search(){
        let input, filter, ul, h3;
        input = document.getElementById("search");
        filter = input.value.toUpperCase();
        /*ul = document.getElementById("workouts");
        h3 = ul.getElementsByTagName("h3");*/

        $('.exercise').hide();
        $(`div[data-title*="${filter}"]`).show();
        //the *= does the same thing as contains or index of.

        //When the input matches the name of an exercise then the whole block will display and the exercise names thay do not match will not display.

        console.log($(`div[data-title*="${filter}"]`));;


        /*for(let i = 0; i < h3.length; i++){
            // li[i].getElementsByTagName('')[0];
            if(h3[i].innerHTML.toUpperCase().indexOf(filter) > -1){
                h3[i].style.display = "";
            } else {
                h3[i].style.display = "none";
            }
        }*/
    }

$("#search").on("keyup", search);
})();