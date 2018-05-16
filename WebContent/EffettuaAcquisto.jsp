<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="model.TipologiaUtente"%>

<jsp:useBean id="compraBean" scope="request" class="bean.CompraBean" />

<jsp:useBean id="loginBean" scope="session" class="bean.LoginBean" />
<jsp:setProperty name="compraBean" property="*" />


<%	if(loginBean.userIsNotLogged() || !(loginBean.getTipo().equals(TipologiaUtente.CONSUMATORE)) || (request.getParameter("effettuaAcquisto") == null) ||
		(compraBean.getIdAnnuncio() == -1) || (compraBean.getIdIndirizzoSpedizione() == -1))	
	{ 
		response.sendRedirect("./Login.jsp");	
	}

	else
	{
	
%>


<!DOCTYPE html>
<html><head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Riepigolo Acquisto</title>

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
    <style>
        .ds-btn li{ list-style:none; float:left; padding:10px; }
.ds-btn li a span{padding-left:15px;padding-right:5px;width:100%;display:inline-block; text-align:left;}
.ds-btn li a span small{width:100%; display:inline-block; text-align:left;}
    </style>
  </head>

  <body style="
    font-family: -apple-system,system-ui,BlinkMacSystemFont,&quot;Segoe UI&quot;,Roboto,&quot;Helvetica Neue&quot;,Arial,sans-serif;
">

    <div class="container">
     <jsp:include page="header.jsp"/>

      <div class="jumbotron">
        



		<%
			
			
        
        	 
			if(!compraBean.validate())
			{
        	%>
	           	<div class="alert alert-danger" role="alert">
	                  <strong>Attenzione.</strong> Il modulo di acquisto contiene uno o più campi vuoti o con valori non validi.
	                  <br>
	                  <%out.print("idAnnuncio: " + compraBean.getIdAnnuncio()); %>
	                  <br>
	                  <%out.print("idIndirizzoSpedizione: " + compraBean.getIdIndirizzoSpedizione()); %>
	                  <br>
	                  <%out.print("Nominativo: " + compraBean.getNominativo()); %>
	                  <br>
	                  <%out.print("Numero carta: " + compraBean.getNumeroCarta()); %>
	                  <br>
	                  <%out.print("Scadenza: " + compraBean.getScadenza()); %>
	                  <br>
	                  <%out.print("CVV: " + compraBean.getCvv()); %>
	                  <br>
	                  <%out.print("Quantità da Acquistare: " + compraBean.getQuantitàDaAcquistare()); %>
	                 
	                  
	                  
	             </div>
	        <%
	        }
	        	  
			else
	        {
	        
				
	        	
	        	int result = compraBean.compra(loginBean.getId());
	        	  		
	        	  		        	  		
			        	if(result == -4)
		              	{
		    	  			
		              		%>
		              	   	<div class="alert alert-danger" role="alert">
		              	          <strong>Transazione annullata.</strong> L'annuncio che stavi comprando potrebbe non essere più disponibile ovvero essere stato modificato.
		              	        </div>
		              	        <%
		              	}

	        	  		if(result == -3)
		              	{
	        	  			
		              		%>
		              	   	<div class="alert alert-danger" role="alert">
		              	          <strong>Transazione annullata.</strong> La tua modalità di pagamento è stata declinata.
		              	        </div>
		              	        <%
		              	}
	        	  		
	        	  		if(result == -2)
		              	{
		              		%>
		              	   	<div class="alert alert-danger" role="alert">
		              	          <strong>Errore.</strong> Hai sottomesso una richiesta non consistente.
		              	        </div>
		              	        <%
		              	}
	        	  		
	        	  		
	        	  		if(result == -1)
		              	{
		              		%>
		              	   	<div class="alert alert-danger" role="alert">
							 <strong>Errore</strong> nel ritrovare l'annuncio specificato o l'indirizzo di spedizione selezionato.
							  
							</div>
		              	        <%
		              	}
	        	  		
	        	  		
	        	  		if(result == 0)
		              	{
		              		%>
		              	   	<div class="alert alert-danger" role="alert">
							 <strong>Errore</strong> nel completare l'operazione. Riprovare più tardi
							  
							</div>
		              	        <%
		              	}
	        	  		
	        	  		
	        	  		if(result == 1)
		              	{
		              		%>
		              	   	<div class="alert alert-success" role="alert">
							  <strong>Operazione eseguita !</strong>
							  <a href="VisualizzaAcquisti.jsp" class="alert-link">Clicca qui per accedere ai tuoi ordini.</a>
							  
							</div>
		              	        <%
		              	}
	        	  		
	        	  		
			}
          
        %>


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

<% 
	} 

%>