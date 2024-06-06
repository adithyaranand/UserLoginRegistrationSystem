<!DOCTYPE html>
<html>
<head>
    <title>Home Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        h1 {
            text-align: center;
            margin-top: 50px;
        }
        .content {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 70vh;
        }
        footer {
            background-color: #007bff;
            color: white;
            text-align: center;
            padding: 10px 0;
            position: absolute;
            bottom: 0;
            width: 100%;
        }
    </style>
</head>
<body>
    <%@ include file="header.jsp" %>

    <div class="content">
        <h1>Welcome to the Home Page</h1>
        
    </div>

    <%@ include file="footer.jsp" %>
</body>
</html>
