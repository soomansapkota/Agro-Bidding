
        function clearErrors() {
            var errors = document.querySelectorAll('.formerror');
            errors.forEach(error => error.textContent = "");
        }

        function seterror(id, error) {
            var element = document.getElementById(id);
            element.querySelector('.formerror').textContent = error;
        }

        function validateForm() {
            var returnval = true;
            clearErrors();

            var cropName = document.forms['adding']['pname'].value;
            if (!cropName.match(/[A-Za-z]+/i)) {
                seterror("pname", "Crop name must contain alphabets only");
                returnval = false;
            }

            var farmName = document.forms['adding']['farm'].value;
            if (!farmName.match(/[A-Za-z]+/i)) {
                seterror("farm", "Farm name must contain alphabets only");
                returnval = false;
            } else if (farmName.length < 2) {
                seterror("farm", "Length of farm name must be at least 2 characters");
                returnval = false;
            }

            var amount = document.forms['adding']['amount'].value;
            if (!amount.match(/[0-9]+/i)) {
                seterror("amount", "Amount must contain numeric value only");
                returnval = false;
            } else if (amount.length < 2) {
                seterror("amount", "Amount must be at least two digits");
                returnval = false;
            }

            var cost = document.forms['adding']['cost'].value;
            if (!cost.match(/[0-9]+/i)) {
                seterror("cost", "Cost must contain numeric value only");
                returnval = false;
            } else if (cost.length < 2) {
                seterror("cost", "Cost must be at least two digits");
                returnval = false;
            }

            var category = document.forms['adding']['category'].value;
            if (!category.match(/[A-Za-z]+/i)) {
                seterror("category", "Category must contain letters only");
                returnval = false;
            } else if (category.length < 4) {
                seterror("category", "Category must be at least four letters");
                returnval = false;
            }

            if (returnval) {
                alert("Product is placed successfully");
            }

            return returnval;
        }
   