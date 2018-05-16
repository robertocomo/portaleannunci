<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>



<jsp:useBean id="loginBean" scope="session" class="bean.LoginBean" />


<jsp:setProperty name="loginBean" property="*" />

<%	if(loginBean.userIsLogged())	
		response.sendRedirect("./index.jsp");	
	

  	if (request.getParameter("login") != null)
  			if(loginBean.validate())
  			{
  				if(loginBean.login())
  					response.sendRedirect("./index.jsp");	
  			}
  				
  	
%>


<!-- HTML 5 -->
<!DOCTYPE html>
<html><head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Login Page</title>

    <!-- Bootstrap core CSS -->
    <link href="./css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="./css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="./css/jumbotron-narrow.css" rel="stylesheet">

   
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body style="
    font-family: -apple-system,system-ui,BlinkMacSystemFont,&quot;Segoe UI&quot;,Roboto,&quot;Helvetica Neue&quot;,Arial,sans-serif;
">

    <div class="container">
     <jsp:include page="header.jsp"/>

      <div class="jumbotron">
      
      
      <%	
      	
      		if (request.getParameter("registrationDone") != null)
      		{	
      			if(request.getParameter("registrationDone").equals("success"))
      			{		
      			%>
              								
              	   	<div class="alert alert-success" role="alert">
					  <strong>Operazione eseguita !</strong> La tua registrazione è stata completata con successo.
					  Accedi ora alla tua area dedicata.
		
					  
					</div>
                <%
      			}
      		}
      
      
	      	if (request.getParameter("redirectDettaglioAnnuncio") != null)
			{	
				if(request.getParameter("redirectDettaglioAnnuncio").equals("true"))
				{		
				%>
	        								
	        	   	<div class="alert alert-warning" role="alert" style="    text-align: justify;">
					  <span style="text-align: center;display:block"><strong>Ciao !</strong></span> <br>Per accedere ai dettagli degli annunci presenti nel portale devi autenticarti come Consumatore.
					  
					  
		
					  
					</div>
					<br>
	          <%
				}
			}
      
      
      		
      		else
      		{
      		%>
				<h1>Bentornato</h1>
            <%
      			
      		}
      %>
      
      
        
        <p class="lead">
</p><form class="form-signin" action="Login.jsp" name="myform" method="POST">
        <h4 class="form-signin-heading">Inserisci le tue credenziali</h4><br>

        <label for="inputEmail" class="sr-only">Email address</label>
        <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required="" autofocus="" name="email">
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" class="form-control" placeholder="Password" required="" name="password" >
        
        <br><br>

        <%
          if (request.getParameter("login") != null) {
        %>
        <div class="alert alert-danger" role="alert">
          <strong>Errore.</strong> Inserisci correttamente le tue credenziali.
        </div>
        <%
          }
        %>


        <button class="btn btn-lg btn-success btn-block center-block" type="submit" style="width: 70% !important;" name="login">Login</button>
      </form>



<p></p>
        <p><a class="btn btn-lg btn-info btn-block center-block" href="Registration.jsp" role="button" style="width: 70% !important;">Registrati Ora</a></p>
      </div>

      

      <footer class="footer">
       <%@include file="footer.html"%>
      </footer>

    </div> <!-- /container -->


    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="./js/ie10-viewport-bug-workaround.js"></script>
     <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="./js/bootstrap.min.js"></script>
  

</body></html>