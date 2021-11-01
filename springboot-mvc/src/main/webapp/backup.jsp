<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
</head>

<body>

    <p id="error"></p>

    <p>
    <form name="createkeyform" action="/createkey" method="post" onsubmit="return validateCreateForm()" modelAttribute="createkey">
        <label for="createkey">Insert key - Key: </label>
        <input type="text" id="createkey" name="createkey" />
        <input type="submit" value="Create Key" />
    </form>

    <c:url var="update_url" value="/updatekey/${updateoldkeyId}"/>
    <form action="${update_url}" method="post" modelAttribute="updatenewkey">
        <label for="updateoldkeyId">Update key - Index: </label>
        <input type="number" id="updateoldkeyId" name="updateoldkeyId" />
        Key:
        <input type="text" id="updatenewkey" name="updatenewkey" />
        <input type="submit" value="Update Key" />
    </form>

    <form action="/deletekey" method="post" modelAttribute="deletekey">
        <label for="deletekey">Delete key - Index: </label>
        <input type="number" id="deletekey" name="deletekey" />
        <input type="submit" value="Delete Key" />
    </form>
    </p>
    <br />

    <!-- <p>${keysString}</p> -->
    <ul>
        <c:forEach items="${keys}" var="key">
            <li>${key.key}</li>
        </c:forEach>
    </ul>

    <script>
        function validateCreateForm(){
            console.log("inside validateCreateForm function");
            var x = document.forms["createkeyform"]["createkey"].value;
            console.log(x);
            if (x == "") {
                // document.getElementById(error).innerHTML = "please enter some input";
                return false;
            }
            return false;
        }
    </script>

</body>

</html>