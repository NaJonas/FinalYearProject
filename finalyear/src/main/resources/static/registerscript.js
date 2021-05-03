



    function addFields() {

        // Delete rows from previous role selection and then add fields for the chosen user account type
        var length = document.getElementById("registrationForm").rows.length;
        console.log(length);
        if (length == 5) {
            document.getElementById("registrationForm").deleteRow(3);
        }
        if (length == 6) {
            document.getElementById("registrationForm").deleteRow(4);
            document.getElementById("registrationForm").deleteRow(3);
        }

        var x = document.getElementById("role");
        console.log(x.value);

        if (x.value == "Customer") {
            document.getElementById("registrationForm").insertRow(3).innerHTML =
                '                <tr>\n' +
                '                        <td><label>First Name:</label></td>\n' +
                '                        <td><input type="text" name="name" required="required"/></td>\n' +
                '                 </tr>';
            document.getElementById("registrationForm").insertRow(4).innerHTML =
                '              <tr>\n' +
                '                     <td><label>Last Name:</label></td>\n' +
                '                     <td><input type="text"  name="surname" required="required"/></td>\n' +
                '                 </tr>';
        }
        else if (x.value == "Restaurant") {
            document.getElementById("registrationForm").insertRow(3).innerHTML =
                '                <tr>\n' +
                '                        <td><label>Company name:</label></td>\n' +
                '                        <td><input type="text" name="companyName" required="required"/></td>\n' +
                '                 </tr>';
        }

    }