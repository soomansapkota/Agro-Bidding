
 function clearErrors() {
    var errors = document.getElementsByClassName('formerror');
    for (let i = 0; i < errors.length; i++) {
      errors[i].innerHTML = "";
    }
  }
  
  function seterror(id, error){
    element = document.getElementById(id);
    element.getElementsByClassName('formerror')[0].innerHTML = error;

}
  
  function validateForm() {
    var returnval = true;
    clearErrors();
    var retailername = document.forms['myForm']['name'].value;
   
       if (!retailername.match(/[A-Za-z]+ [A-Za-z]+ [A-Za-z]+/i)) {//regex for name middlename lastname and also space between them but not numeric value
        seterror("fname", "*Name must contain alphabets only");
       returnval = false;
     }
     else  if (retailername.length < 3) {
        seterror("fname", "*Length of username must be at least of 3 characters");
        returnval = false;
    }

  
     var username = document.forms['myForm']['username'].value;
    
     if (!username.match(/[A-Za-z]+/i)) {
      seterror("fusername", "*Username must contain alphabets only");
     returnval = false;
   }
   else  if (username.length < 3) {
    seterror("fusername", "*Length of username must be at least 3 characters");
    returnval = false;
 }
  
     var address = document.forms['myForm']['address'].value;
     if (!address.match(/[A-Za-z]+-\d\d,[A-Za-z]+/i)) {
      seterror("fadress", "*Address must be in formate [CityName-wardno,district]");
      returnval = false;
     }
    var pan = document.forms['myForm']['pan'].value;
     if(!pan.match(/[0-9]+/i)){
      seterror("fpan","pan numbers only contain numeric values")
     }
     else  if(pan.length>10){
      seterror("fpan","pan numbers can be maximum of 10 digit")
       returnval = false;
      }
     var cnumber = document.forms['myForm']['cnumber'].value;{
      if(!cnumber.match(/[0-9]+/i)){
        seterror("fcitizenno","citizen numbers only contain numeric values")
       }
       else  if(cnumber.length<3){
        seterror("fcitizenno","citizenship number cannot be less than 3 digits")
         returnval = false;
        }
     }
      var password = document.forms['myForm']['password'].value;
     if (password.length < 6) {
       seterror("fpassword", "*Length of password must be at least 8 characters");
       returnval = false;
     }
   else if(!password.match(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/)){
    seterror("fpassword", "*password must contain atleast one capital,one small letter,one numericvalue and one special charecter");
    returnval = false;
    }
    return returnval;
  }