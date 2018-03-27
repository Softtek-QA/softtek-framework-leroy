function autGetItem(tagItemProcurado,textoProcurado,numeroDaOcorrenciaDoItem){
	try{cont=0;numOcorrencia=numeroDaOcorrenciaDoItem;contOcorrencias=0;tot=document.getElementsByTagName

	(tagItemProcurado).length;itens=document.getElementsByTagName(tagItemProcurado);output="";while(cont<tot)

	{console.log(itens[cont]);output+=itens[cont];cont++;strItem=itens[cont].innerHTML.toString();if

	(strItem.indexOf(textoProcurado) >= 0){console.log("***** encontrado *****");if

	(contOcorrencias==numOcorrencia){console.log("@@@**** ATIVANDO ITEM:");itens[cont].click();}contOcorrencias++}};console.log

	(output);}catch(exp){}
}
