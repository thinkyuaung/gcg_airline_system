document.addEventListener("DOMContentLoaded", function(){

    loadCategories();

});


function loadCategories(){

	console.log("Calling categories API");   
	
    fetch("/chatbot/categories")

    .then(response => response.json())

    .then(data => {


        let box = document.getElementById("chatOptions");


        box.innerHTML = "";


        data.forEach(category => {


            let button = document.createElement("button");


            button.className = "option-btn";


            button.innerHTML =
                category.icon + " " + category.name;


            button.onclick = function(){

                loadQuestions(category.categoryId);

            };


            box.appendChild(button);


        });


    });


}



function loadQuestions(categoryId){


    fetch("/chatbot/questions/" + categoryId)

    .then(response => response.json())

    .then(data => {


        let box = document.getElementById("chatOptions");


        box.innerHTML = "";


        data.forEach(question => {


            let button = document.createElement("button");


            button.className="option-btn";


            button.innerHTML =
                question.question;


            button.onclick=function(){

                loadAnswer(question.questionId);

            };


            box.appendChild(button);


        });


    });


}



function loadAnswer(questionId){


    fetch("/chatbot/answer/" + questionId)

    .then(response => response.json())

    .then(data => {


        let box =
        document.getElementById("chatOptions");


        box.innerHTML = `

        <div class="bot-message">

            ${data.answer}

        </div>


        <button class="option-btn"
                onclick="loadCategories()">

            Another Question

        </button>

        `;


    });


}