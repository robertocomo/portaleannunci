<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  
<%@ page import="model.TipologiaUtente"%> 
<jsp:useBean id="visualizzaAcquistiBean" scope="page" class="bean.VisualizzaAcquistiBean" />
<jsp:useBean id="loginBean" scope="session" class="bean.LoginBean" />


<%	if(loginBean.userIsNotLogged() || !(loginBean.getTipo().equals(TipologiaUtente.CONSUMATORE)))	
	{ 
		response.sendRedirect("./Login.jsp");	
	}
%> 
   


<!DOCTYPE html>
<html><head>
  <title>Visualizza Acquisti</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link href="./css/jumbotron-narrow.css" rel="stylesheet">
<style>

		@media (min-width: 768px){
		.container {
		    max-width: 970px;
		} }


	.noHover:hover {
  		background-color: #e1a92f !important;
	}

  /* mixin for multiline */
    .block-with-text {
      overflow: hidden;
      position: relative;
      line-height: 1.2em;
      max-height: 2.4em;
      text-align: justify;
      margin-right: -1em;
      padding-right: 1em;
    }

    .block-with-text:before {
        content: '...';
        position: absolute;
        right: 0;
        bottom: 0;
    }

    .block-with-text:after {
        content: '';
        position: absolute;
        right: 0;
        width: 1em;
        height: 1em;
        margin-top: 0.2em;
        background: #eee;
    }



    /* Set height of the grid so .sidenav can be 100% (adjust if needed) */
    .row.content {height: 1500px}
    
    /* Set gray background color and 100% height */
    .sidenav {
      background-color: #f1f1f1;
      height: 100%;
    }
    
    /* Set black background color, white text and some padding */
    footer {
      /*background-color: #555;
      color: white;*/
      padding: 15px;
      list-style: none;
    }
    
    footer li {
	  border-left: solid 1px;
	  display: inline-block;
	  line-height: 1em;
	  margin-left: 1em;
	  padding: 0 0 0 1em;
	}
	
	footer li:first-child {
	  border-left: 0;
	  margin-left: 0;
	  padding: 0;
	}
    
    /* On small screens, set height to 'auto' for sidenav and grid */
    @media screen and (max-width: 767px) {
      .sidenav {
        height: auto;
        padding: 15px;
      }
      .row.content {height: auto;} 
    }
  </style>
</head>
<body style="
    font-family: -apple-system,system-ui,BlinkMacSystemFont,&quot;Segoe UI&quot;,Roboto,&quot;Helvetica Neue&quot;,Arial,sans-serif;
">
    <div class="container">
<jsp:include page="header.jsp"/>
    
    
    
    </div>


<div class="container">
<div class="jumbotron">
    
    
    <%
    
    		if(!visualizzaAcquistiBean.trovaAcquisti(loginBean.getId()))
    		{
    		%>
        	   	<div class="alert alert-warning" role="alert">
        	          Non hai effettuato ancora alcun acquisto.
        	    </div>
        	<%	
    		}
    		
    		else
    		{
    			visualizzaAcquistiBean.ordinaPerUltimoOrdine();
    			
    			while(visualizzaAcquistiBean.estraiOrdine())
    			{
    			%>	
		    				<blockquote style="border-left: 5px solid #37c9a4;text-align: left;margin-bottom: 0; padding-left:3em">
		    				<div class="row">
    						<div class="col-sm-7"> 
											<p class="text-center block-with-text" style="font-weight: 400;font-size: 1.3em;letter-spacing: -1px;font-style: italic;"><%
											out.print(visualizzaAcquistiBean.getTitolo());%></p>
											<div class="row" style="
							    font-size: 1.1em;
							    font-weight: 300;
							    /* margin-top: 1em; */
							    padding-top: 1.1em;
							    letter-spacing: 1px;
							">
							  <div class="col-sm-5">Importo :</div>
							  <div class="col-sm-7" style="font-weight: 300;"><%
											out.print(visualizzaAcquistiBean.getPrezzo() + " ");%></div>
							
							</div>
											
							
							<div class="row" style="
							    font-size: 1.1em;
							    font-weight: 300;
							    padding-top: 0.4em;
							    letter-spacing: 1px;
							">
							  <div class="col-sm-5">Quantità :</div>
							  <div class="col-sm-7"><%
											out.print(visualizzaAcquistiBean.getQuantità());%></div>
							
							</div>
							
							<div class="row" style="
							    font-size: 1.1em;
							    font-weight: 300;
							    padding-top: 0.4em;
							    letter-spacing: 1px;
							">
							  <div class="col-sm-5">Importo Totale :</div>
							  <div class="col-sm-7" style="font-weight: 400;"><%
											out.print(visualizzaAcquistiBean.getPrezzoComplessivo() + " ");%> <span class="glyphicon glyphicon-euro" style="
				    font-size: small;
				    top: 0;
				    /* padding-left: 0.2em; */
				"></span></div>
							
							</div>
							
							
							
							<div class="row" style="
							    font-size: 1.1em;
							    font-weight: 300;
							    padding-top: 1.4em;
							    letter-spacing: 1px;
							">
							  <div class="col-sm-5"><span class="glyphicon glyphicon-gift" style="
											    font-size: small;
											    top: 0;
											    font-weight: 100;
											    margin-right: 1em;
											"></span>Spedito:</div>
							  <div class="col-sm-7"><span class="glyphicon glyphicon-<%out.print(visualizzaAcquistiBean.isShipped() ? "ok" : "remove");%>" style="
											    font-size: small;
											    top: 0;
											    /* padding-left: 0.2em; */
											"></span></div>
							
							</div>
							
							<div class="row" style="
							    font-size: 1.1em;
							    font-weight: 300;
							    padding-top: 0.4em;
							    letter-spacing: 1px;
							">
							  <div class="col-sm-5"><span class="glyphicon glyphicon-barcode" style="
											    font-size: small;
											    top: 0;
											    font-weight: 100;
											    margin-right: 1em;
											"></span>Tracking:</div>
							  <div class="col-sm-7"><%
							  if(visualizzaAcquistiBean.isShipped())
							  {
								  out.print(visualizzaAcquistiBean.getTracking());
							  }
							  else 
							  {
								%>
								  		<span class="glyphicon glyphicon-remove" style="
											    font-size: small;
											    top: 0;
											    /* padding-left: 0.2em; */
											"></span>
								  
								<%
							  }
							  %></div>
							
							</div>
							</div>
							<div class="col-sm-5"><img class="img-thumbnail img-responsive d-flex align-self-start mr-3" src="<%out.print(visualizzaAcquistiBean.getFoto()); %>" alt="Immagine non disponibile" style="
								    margin: 0 auto;
								    justify-content: center;
								    display: flex;
								    /* padding: 0.5em; */
								    margin-top: 1.5em;
								    width: auto;
								    max-height: 210px;
								    text-align: center;
								    /* max-width: 550px; */
								"></div>
								</div>
								<div class="row" style="
								    font-size: 1.1em;
								    font-weight: 300;
								    /* margin-top: 1em; */
								    padding-top: 0.8em;
								    letter-spacing: 1px;
								">
								    <div class="col-sm-3"><span class="glyphicon glyphicon-envelope" style="
											    font-size: small;
											    top: 0;
											    font-weight: 100;
											    margin-right: 1em;
											"></span>Indirizzo:</div>
								        <div class="col-sm-7" style="
								    font-size: 0.9em;
								    letter-spacing: -0.05em;
								    font-style: italic;
								    font-weight: 100;
								"><% out.print(visualizzaAcquistiBean.getIndidizzoSpedizione()); %>
								</div>
								</div>
										</blockquote>
										<span style="
												    text-align: right;
												    font-size: 0.8em;
												    font-weight: 400;
												    /* margin: 0; */
												    color: white;
												    white-space: nowrap;
												    vertical-align: baseline;
												    border-radius: 2rem;
												    background-color: #4fa9d9;
												    float: right;
												    padding: 0.2em 0.6em 0.2em 0.6em;
												    font-style: italic;
												    letter-spacing: 0.02em;
												    "><% out.print(visualizzaAcquistiBean.getDate()); %></span>
								<legend style="margin-bottom: 2.5em;margin-top: 1.4em !important;"></legend>
    				
    				
    				
    				
    			<%	
    			}
    			
    			
    			
    			
    		}
   			
    
    %>
    
    	
    		
    	
    		
    		
    


      </div></div>


<footer class="container-fluid">
  <%@include file="footer.html"%>
</footer>






</body></html>