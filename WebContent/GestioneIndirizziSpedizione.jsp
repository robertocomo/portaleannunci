<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  
<%@ page import="model.TipologiaUtente"%>
<%@ page import="bean.IndirizzoSpedizioneBean"%>  

<jsp:useBean id="gestioneIndirizzoSpedizioneBean" scope="request" class="bean.GestioneIndirizzoSpedizioneBean" />
<jsp:useBean id="loginBean" scope="session" class="bean.LoginBean" />


<%	if(loginBean.userIsNotLogged() || !(loginBean.getTipo().equals(TipologiaUtente.CONSUMATORE)))	
	{ 
		response.sendRedirect("./Login.jsp");	
	}
%> 
   
    
    
<!DOCTYPE html>
<html><head>
  <title>I tuoi indirizzi</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link href="./css/jumbotron-narrow.css" rel="stylesheet">
<style>


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
        background: white;
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
    <div class="container-fluid">
<jsp:include page="header.jsp"/>
    
    
    
    </div>


<div class="container-fluid">
<div class="row content" style="
    /* background-color: #f1f1f1; */
    height: 100%;
    margin-left: 3.5em;
    margin-right: 3.5em;
    padding-top: 0.5em;
    width: 70%;
">
    



<div class="col-sm-3 sidenav"style="background-color: transparent;"><br>
<br>

<br>


<ul class="list-group">
  <li class="list-group-item active" style="text-align: center;font-weight: 400;font-size: 1.2em;">I tuoi indirizzi</li>
  <li class="list-group-item"><a href="GestioneIndirizziSpedizione.jsp">Visualizza Indirizzi</a></li>
  <li class="list-group-item"><a href="AggiungiIndirizzoSpedizione.jsp"> Nuovo Indirizzo</a></li>
</ul>
    </div>

    <div class="col-sm-1"></div>
<div class="col-sm-7" style="
    /*background-color: rgba(60, 181, 213, 0.84);*/
    /* height: inherit; */
    /* overflow-y: auto; */
">
  
    
    <div class="jumbotron">
    
    
    <%
    
    	int result = gestioneIndirizzoSpedizioneBean.cercaIndirizzi(loginBean.getId());
    
    	if(result == -1)
    	{	
    		%>
    	   	<div class="alert alert-danger" role="alert">
    	          <strong>Errore</strong> nel sottomettere la richiesta.
    	        </div>
    	        <%

    	}
    	
    	if(result == 0)
    	{
    		%>
    	   	<div class="alert alert-warning" role="alert">
    	          <strong>Attenzione.</strong> Non hai ancora associato alcun indirizzo.
    	        </div>
    	        <%

    	}
    	
    	
    	for(IndirizzoSpedizioneBean indirizzo : gestioneIndirizzoSpedizioneBean.getRisultati())
    	{
    	%>	
    		
    		<blockquote style="border-left: 5px solid #1070ea;text-align: left;margin-bottom: 0;">
				<p style="font-weight: 400;font-size: 1.3em;"><%out.print(indirizzo.getNominativo()); %>
				<br>
				<% if(!indirizzo.getPresso().trim().isEmpty())
				{
					out.print("c\\o " + indirizzo.getPresso());
				}
				%>
				</p>
				<p style="margin: 0;padding: 0;font-size: 0.9em;"><%out.print(indirizzo.getVia()); %></p>
				<p style="/* margin-left: 2em; */text-align: left;padding: 0;margin: 0;font-size: 0.9em;"><%out.print(indirizzo.getCittà() + " "); %>
				<%out.print(indirizzo.getCap() + " "); %><%out.print(indirizzo.getProvincia() + " "); %></p>
			</blockquote>
			<span style="
					    text-align: right;
					    font-size: 0.8em;
					    font-weight: 400;
					    margin: 0;
					    color: white;
					    white-space: nowrap;
					    vertical-align: baseline;
					    border-radius: 2rem;
					    background-color: #d9534f;
					    float: right;
					    padding: 0.2em 0.6em 0.2em 0.6em;
					    font-style: italic;
					    letter-spacing: 0.02em;">Modifica</span>
	<legend style="margin-bottom: 2em;margin-top: 1.4em !important;"></legend>
	
    	<%	
    	}
    
    
    
    %>
    
    
       


				
        


      </div>
    
    
    
    
    
    

    
    </div>
<div class="col-sm-1"></div>
  </div>
</div>

<footer class="container-fluid">
 <%@include file="footer.html"%>
</footer>

</body></html>