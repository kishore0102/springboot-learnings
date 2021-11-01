<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
</head>

<body>

    <p id="error"></p>

    <p>
        <form id="createkeyForm" action="/createkey" method="post" 
            onsubmit="return validateCreateForm()" modelAttribute="createkeytext">
            <label for="createkeytext">Insert key - Key: </label>
            <input type="text" id="createkeytext" name="createkeytext" />
            <input type="submit" value="Create Key" />
        </form>

        <form id="updatekeyForm" action="/updatekey" method="post" 
            onsubmit="return validateUpdateForm()" modelAttribute="updatekeytext">
            <label for="updateoldkeyId">Update key - Index: </label>
            <input type="number" id="updateoldkeyId" name="updateoldkeyId" />
            Key:
            <input type="text" id="updatekeytext" name="updatekeytext" />
            <input type="submit" value="Update Key" />
        </form>

        <form id="deletekeyForm" action="/deletekey" method="post" 
            onsubmit="return validateDeleteForm()" modelAttribute="deletekeytext">
            <label for="deletekeytext">Delete key - Index: </label>
            <input type="number" id="deletekeytext" name="deletekeytext" />
            <input type="submit" value="Delete Key" />
        </form>
    </p>

    <br />

    <!-- <p>${keysString}</p> -->
    <ul id="keysul">
        <c:forEach items="${keys}" var="key">
            <li>${key.key}</li>
        </c:forEach>
    </ul>

    <script>
        function validateCreateForm() {
            var create_key = document.getElementById("createkeytext").value;
            if(create_key.length == 0) {
                console.log("length is zero");
                document.getElementById("error").innerHTML = "please enter some input";
                return false;
            }
            return true;
        }

        function validateDeleteForm() {
            var delete_key = document.getElementById("deletekeytext").value;
            var ullen = document.getElementById("keysul").getElementsByTagName("li").length;
            console.log(ullen);

            if(delete_key > ullen) {
                console.log("index not found");
                document.getElementById("error").innerHTML = "index not found";
                return false;
            }

            if(delete_key.length == 0) {
                console.log("length is zero");
                document.getElementById("error").innerHTML = "please enter some input";
                return false;
            }
            return true;
        }

        function validateUpdateForm() {
            var update_key_text = document.getElementById("updatekeytext").value;
            var update_key_id = document.getElementById("updateoldkeyId").value;

            document.getElementById("updatekeyForm").action = "/updatekey/" + update_key_id;

            if(update_key_text.length == 0) {
                console.log("length is zero");
                document.getElementById("error").innerHTML = "please enter some input";
                return false;
            }
            return true;
        }
    </script>

</body>

</html>