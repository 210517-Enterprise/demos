// 1. Build a function called addStudent

let idCounter =1;

function addStudent() {

    // Grab the unput field values
    let studentNameInputField = document.getElementById('student-name');
    // how do we grab the value from the input field?
    let studentName = studentNameInputField.value;

    console.log(studentName);

    // validate the values (ensure that they're not empty strings)

        // // Create a row and cells for the students table with document.crearteElement

            // append the cells to the row


            // clear the input for the fields for future values to be provided




}



// create an event listener that fire when the button is clicked
document.getElementById('add-student').addEventListener("click", addStudent); // <---- example of a callback function!