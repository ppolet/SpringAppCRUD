<html>
<body>
<h2>Hello World!</h2>

<form th:method="POST" th:action="@{/people}" th:object="${person}">
    <label for="login">Enter login: </label>
    <input type="text" th:field="*{login}" id="login"/>
    <br/>
    <label for="password">Enter password: </label>
    <input type="text" th:field="*{password}" id="password"/>
    <br/>
    <input type="submit" value="Connect!"/>
</form>

</body>
</html>
