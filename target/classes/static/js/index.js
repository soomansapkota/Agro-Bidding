function clearErrors() {
    var errors = document.getElementsByClassName('formerror');
    for (var i = 0; i < errors.length; i++) {
        errors[i].innerHTML = "";
    }
}

function seterror(id, error) {
    // Set error message inside the element with the given id
    var element = document.getElementById(id);
    element.getElementsByClassName('formerror')[0].innerHTML = error;
}

function validateForm() {
    var returnval = true;
    clearErrors();

    var alphaletters = /^[a-zA-Z]*$/;

    var name = document.forms['myForm']["username"].value;
    if (name.length < 4) {
        seterror("name", "*username must be of least 4 characters");
        returnval = false;
    } else if (!name.match(alphaletters)) {
        seterror("name", "*Username must contain alphabets only");
        returnval = false;
    }
    
        var na = document.forms['myForm']["name"].value;
    if (na.length < 4) {
        seterror("name", "*username must be of least 4 characters");
        returnval = false;
    } else if (!na.match(alphaletters)) {
        seterror("name", "*Username must contain alphabets only");
        returnval = false;
    }
            var address = document.forms['myForm']["address"].value;
    if (address.length < 4) {
        seterror("address", "*Address must be of least 4 characters");
        returnval = false;
    } else if (!address.match(alphaletters)) {
        seterror("address", "*Address must contain alphabets only");
        returnval = false;
    }
    
            var name = document.forms['myForm'][""].value;
    if (name.length < 4) {
        seterror("name", "*username must be of least 4 characters");
        returnval = false;
    } else if (!name.match(alphaletters)) {
        seterror("name", "*Username must contain alphabets only");
        returnval = false;
    }

    var email = document.forms['myForm']["email"].value;
    if (email.length > 45) {
        seterror("email", "*Email length is too long");
        returnval = false;
    }
    var emailcharacter = /^[A-Za-z\._\-0-9]*[@][A-Za-z]*[\.][a-z]{2,4}$/;
    if (!email.match(emailcharacter)) {
        seterror("email", "*Email format does not match");
        returnval = false;
    }
    var citizenship = document.forms['myForm']["citizenshipnumber"].value;
    if (citizenship.length <= 2) {
        seterror("citizenship", "*Citizenship must be greater than 2 digits!");
        returnval = false;
    }
    var password = document.forms['myForm']["password"].value;
    if (password.length < 6) {
        seterror("pass", "*Password should be at least 6 characters long!");
        returnval = false;
    }
    return returnval;
}
