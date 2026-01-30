<%-- Calculator Web Service Client - View Only --%>
    <%@page contentType="text/html" pageEncoding="UTF-8" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Calculator Web Service Client</title>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    max-width: 800px;
                    margin: 50px auto;
                    padding: 20px;
                    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                    min-height: 100vh;
                }

                .container {
                    background: white;
                    padding: 30px;
                    border-radius: 15px;
                    box-shadow: 0 10px 30px rgba(0, 0, 0, 0.3);
                }

                h1 {
                    color: #333;
                    text-align: center;
                }

                h2 {
                    color: #555;
                    margin-top: 25px;
                }

                .result-box {
                    background: #f8f9fa;
                    border-left: 4px solid #667eea;
                    padding: 15px;
                    margin: 10px 0;
                    border-radius: 5px;
                }

                .operation {
                    font-weight: bold;
                    color: #667eea;
                }

                .value {
                    color: #28a745;
                    font-size: 1.2em;
                    font-weight: bold;
                }

                .greeting {
                    background: #e8f5e9;
                    border-left: 4px solid #28a745;
                    padding: 15px;
                    margin: 20px 0;
                    border-radius: 5px;
                    font-size: 1.1em;
                }

                .error {
                    background: #ffebee;
                    border-left: 4px solid #dc3545;
                    padding: 15px;
                    color: #dc3545;
                }

                .custom-result {
                    background: #fff3e0;
                    border-left: 4px solid #ff9800;
                    padding: 15px;
                    margin: 20px 0;
                    border-radius: 5px;
                }

                .form-box {
                    background: #e3f2fd;
                    padding: 20px;
                    border-radius: 10px;
                    margin: 20px 0;
                }

                input[type="number"] {
                    width: 80px;
                    padding: 8px;
                    margin: 5px;
                    border: 1px solid #ccc;
                    border-radius: 5px;
                }

                select,
                button {
                    padding: 8px 15px;
                    margin: 5px;
                    border-radius: 5px;
                }

                button {
                    background: #667eea;
                    color: white;
                    border: none;
                    cursor: pointer;
                }

                button:hover {
                    background: #5a6fd6;
                }
            </style>
        </head>

        <body>
            <div class="container">
                <h1>Calculator Web Service Demo</h1>

                <% Boolean success=(Boolean) request.getAttribute("success"); %>

                    <% if (success !=null && success) { %>

                        <div class="greeting">
                            <%= request.getAttribute("greeting") %>
                        </div>

                        <!-- Form tinh toan tu nhap -->
                        <div class="form-box">
                            <h3>Thu tinh toan:</h3>
                            <form action="calculator" method="post">
                                <input type="number" name="a" placeholder="So A" required>
                                <select name="operation">
                                    <option value="add">+</option>
                                    <option value="subtract">-</option>
                                    <option value="multiply">x</option>
                                    <option value="divide">/</option>
                                </select>
                                <input type="number" name="b" placeholder="So B" required>
                                <button type="submit">Tinh</button>
                            </form>
                        </div>

                        <!-- Hien thi ket qua tu nhap neu co -->
                        <% if (request.getAttribute("customResult") !=null) { %>
                            <div class="custom-result">
                                <strong>Ket qua cua ban:</strong>
                                <%= request.getAttribute("customA") %>
                                    <%= request.getAttribute("customOp") %>
                                        <%= request.getAttribute("customB") %>
                                            = <span class="value">
                                                <%= request.getAttribute("customResult") %>
                                            </span>
                            </div>
                            <% } %>

                                <h2>Ket qua cac phep tinh mac dinh:</h2>

                                <div class="result-box">
                                    <span class="operation">Phep cong:</span>
                                    10 + 5 = <span class="value">
                                        <%= request.getAttribute("sum") %>
                                    </span>
                                </div>

                                <div class="result-box">
                                    <span class="operation">Phep tru:</span>
                                    20 - 8 = <span class="value">
                                        <%= request.getAttribute("diff") %>
                                    </span>
                                </div>

                                <div class="result-box">
                                    <span class="operation">Phep nhan:</span>
                                    7 x 6 = <span class="value">
                                        <%= request.getAttribute("product") %>
                                    </span>
                                </div>

                                <div class="result-box">
                                    <span class="operation">Phep chia:</span>
                                    100 / 4 = <span class="value">
                                        <%= request.getAttribute("quotient") %>
                                    </span>
                                </div>

                                <hr>

                                <h2>Phep tinh khac:</h2>

                                <div class="result-box">
                                    <span class="operation">15 + 25 =</span>
                                    <span class="value">
                                        <%= request.getAttribute("sum2") %>
                                    </span>
                                </div>

                                <div class="result-box">
                                    <span class="operation">100 - 37 =</span>
                                    <span class="value">
                                        <%= request.getAttribute("diff2") %>
                                    </span>
                                </div>

                                <div class="result-box">
                                    <span class="operation">12 x 12 =</span>
                                    <span class="value">
                                        <%= request.getAttribute("product2") %>
                                    </span>
                                </div>

                                <div class="result-box">
                                    <span class="operation">50 / 3 =</span>
                                    <span class="value">
                                        <%= String.format("%.2f", request.getAttribute("quotient2")) %>
                                    </span>
                                </div>

                                <% } else { %>

                                    <div class="error">
                                        <strong>Loi ket noi Web Service:</strong><br>
                                        <%= request.getAttribute("errorMessage") %><br><br>
                                            <strong>Hay chac chan rang:</strong>
                                            <ol>
                                                <li>WS_Lab312 (Server) da duoc deploy va dang chay</li>
                                                <li>Da tao Web Service Reference tu WSDL</li>
                                                <li>URL: http://localhost:8080/WS_Lab312/CalculatorService?wsdl</li>
                                            </ol>
                                            <br>
                                            <a href="calculator">Thu lai</a>
                                    </div>

                                    <% } %>

                                        <hr>
                                        <p style="text-align: center; color: #666;">
                                            <strong>Mo hinh MVC:</strong><br>
                                            Servlet (Controller) -> Goi Web Service -> Luu vao Request -> Forward sang
                                            JSP (View)
                                        </p>
            </div>
        </body>

        </html>