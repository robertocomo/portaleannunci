<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<jsp:useBean id="loginBean" scope="session" class="bean.LoginBean" />

<%@ page import="model.TipologiaUtente"%>
<%@ page import="model.CategoriaAnnuncio"%>     

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Homepage</title>

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

<%

	if(loginBean.userIsNotLogged())
	{
	%>	
		
			<div class="jumbotron">
	        <h1>Ciao ! Benvenuto</h1>
	        <p class="lead" style="
	    /* letter-spacing: 1px; */
	    font-style: italic;
	    padding-top: 0.5em;
	    /* text-align: justify; */
	">Portale Annunci è un'innovativa applicazione per inserire i tuoi annunci di vendita ovvero acuistare i prodotti messi a disposizione tra le varie categorie del portale.
	
	</p>
	        <p><br><a class="btn btn-lg btn-success" href="Registration.jsp" role="button">Registrati Ora</a></p>
	      </div>
			
		
		
		
	<%	
	}

%>
      

      <div class="jumbotron" style="  background-color: #f4511e;">
      
      <%
      
      	if(loginBean.userIsLogged())
      	{
      	%>	
      		<h1 style="
			    color: white;
			    font-weight: 100;
			    letter-spacing: 0.1em;
			    font-style: italic;
			    padding: 0;
			    margin: 0;
			    margin-bottom: 1.1em;
			">Ricerca</h1>
 		
      	<%	
      	}
      	
     
      %>
      
      
        
        <form class="form-horizontal" action="RisultatiRicerca.jsp" name="myform" method="POST">
    <div class="form-group">
    
    <input class="form-control input-lg" id="title" name="title" type="text" placeholder="Cosa vuoi cercare ?">
  </div>


  

  <div class="form-group" style="text-align: left !important;padding-left: 0 !important;margin-top: 20px !important;">
  <label for="category" style="text-align: left !important;">Categoria</label>
  <select class="form-control" id="category" name="category">
  		<option value="">Tutte le categorie</option>
    	<%
    	 	for (CategoriaAnnuncio type : CategoriaAnnuncio.values())
    	 	{
    		%>	 
    			<option value="<%out.print(type.name()); %>"><%out.print(CategoriaAnnuncio.getCapitalizedName(type.name())); %></option> 
    		<%
    	 	}
    	
    	
    	%>
  </select>
</div>
<div class="form-group" style="text-align: left !important;padding-left: 0 !important;margin-top: 20px !important;"> 
    
      <div class="checkbox">
        <label><input type="checkbox" name="extendToDescription" value="true">Cerca anche nella descrizione</label>
    
    </div>
  </div>
  <br><br>
<div class="row" style="display: flex;justify-content: center;">
  <div class="col-sm-4">
	<button name="search" type="submit" value="search" class="btn btn-success btn-lg" style="display: block;width: 100%;padding: 0.5em 0 0.5em 0;">
    	<span class="glyphicon glyphicon-search"></span> Search
	</button>
 </div>
   <div class="col-sm-1"></div>
	<div class="col-sm-4">
 		<button type="reset" class="btn btn-lg btn-info btn-block center-block" style="display: block;width: 100%;padding: 0.5em 0 0.5em 0;">Reset
        </button>
    </div>
    
  </div>
  
</form>
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
  </body>
</html>
