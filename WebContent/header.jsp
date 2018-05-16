<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="model.TipologiaUtente"%>	
	
	
<jsp:useBean id="loginBean" scope="session" class="bean.LoginBean" />


<div class="header clearfix">
        <nav>
          <ul class="nav nav-pills pull-right">
            <li role="presentation" class="active"><a href="index.jsp">Home</a></li>
            
            
            <%
            	if(loginBean.userIsNotLogged())
            	{
            	%>	
            		<li role="presentation"><a href="Login.jsp">Sign in</a></li>
            		<li role="presentation"><a href="Registration.jsp">Sign up</a></li>
            	<%	
            	}
            		
     
            	if(loginBean.userIsLogged())
            	{
            		%>
            		
            		 <li role="presentation" style="padding-left: 2em;"><div class="dropdown dropdown-menu-right">
			            <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="true">Il mio Account      
			       		 <span class="glyphicon glyphicon-user"></span>  <span class="caret"></span></button>
			            <ul class="dropdown-menu pull-right">
            	<%	
            		if(loginBean.getTipo().equals(TipologiaUtente.PRODUTTORE))
            		{
            		%>
	           		 	<li class="dropdown-header">I miei annunci</li>
			        	<li><a href="AnnunciProduttore.jsp">Visualizza Annunci</a></li>
				        <li><a href="AggiungiAnnuncio.jsp">Nuovo Annuncio</a></li>
				        <li class="divider"></li>
				        <li class="dropdown-header">I miei ordini</li>
						<li><a href="#">Visualizza Ordini</a></li>
						
				        
              		<%
            			
            		}
            	
            		if(loginBean.getTipo().equals(TipologiaUtente.CONSUMATORE))
            		{
            			%>
            			<li class="dropdown-header">Indirizzi Spedizione</li>
						<li><a href="GestioneIndirizziSpedizione.jsp">Gestisci Rubrica Indirizzi</a></li>
						<li class="divider"></li>
						<li class="dropdown-header">I miei acquisti</li>
						<li><a href="VisualizzaAcquisti.jsp">Visualizza Acquisti</a></li>
					<%
            			
            		}
            	
            		
            		%>
            			
						<li class="divider"></li>	
						<li><a href="Logout.jsp">Logout</a></li>
			            </ul>
			            </div></li>
					<%
            		
            		
            	}

            
            %>
           </ul>
        </nav>
        <h3 class="text-muted">Portale Annunci</h3>
      </div>