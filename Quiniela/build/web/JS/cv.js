                function cambiarColor(activar,desactivar){
                    
                    document.getElementById(activar).style.background='#1a648a';
                    document.getElementById(desactivar).style.background='transparent';
                    
                }
                function mostrarMarcador(id){
                    
                    var radios = document.daniela.elements["Sm"];
                    var y;
                    if(document.getElementById("S"+id).checked){
                        document.getElementById("M"+id).style.display='block';
                        document.getElementById("m"+id).style.display='block';
                    }
                    for (var i=0, len=radios.length; i<len; i++) {
                        if (!radios[i].checked) {
                            y = i+1;
                            document.getElementById("M"+y).style.display='none';
                            document.getElementById("m"+y).style.display='none';
                        }
                    }
                }
		function disableselect(e){ 
		return false 
		} 
		function reEnable(){ 
		return true 
		} 
		document.onselectstart=new Function ("return false") 
		if (window.sidebar){ 
		document.onmousedown=disableselect 
		document.onclick=reEnable 
		} 
                