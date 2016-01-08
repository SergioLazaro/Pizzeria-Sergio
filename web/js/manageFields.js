window.onload = function() {
    document.getElementById('pizzaInput').style.display = 'none';
    document.getElementById('pizzaSelector').style.display = 'none';
    document.getElementById('priceInput').style.display = 'none';
    document.getElementById('ingredientsInput').style.display = 'none';
    document.getElementById('button').style.display = 'none';
}

//First of all, we populate the selector
    var pizzas = [];
    var pizzasIndex = [];
    $(document).ready(function(){
        $.get("../populateSelector",function(data){ //data = JSON array
            //Populating selector tag
            $.each(data,function(index,item){   //Iterate over the JSON array
                var array = $.map(item,function(el){ 
                    return el;
                });
                pizzasIndex[index] = array[0];
                pizzas[index] = array[1];                
            });
            var selector = document.getElementById('selector');
            for(var i = 0; i < pizzas.length; i++){
                var option = document.createElement("option");
                option.value = pizzasIndex[i];
                option.innerHTML = pizzas[i];
                selector.appendChild(option);
            }
        });
    });

function check() {
    if (document.getElementById('insertselected').checked) {
        document.getElementById('pizzaInput').style.display = 'block';
        document.getElementById('pizzaSelector').style.display = 'none';
        document.getElementById('priceInput').style.display = 'block';
        document.getElementById('ingredientsInput').style.display = 'block';
        document.getElementById('button').style.display = 'block';
        //Now, we set block fields as required
        document.getElementById('pizzaInputField').required = true;
        document.getElementById('priceInputField').required = true;
        document.getElementById('ingredientsInputField').required = true;
    } 
    else if(document.getElementById('modifyselected').checked) {
        document.getElementById('pizzaInput').style.display = 'none';
        document.getElementById('pizzaSelector').style.display = 'block';
        document.getElementById('priceInput').style.display = 'block';
        document.getElementById('ingredientsInput').style.display = 'block';
        document.getElementById('button').style.display = 'block';
        //Now, we set block fields as required
        document.getElementById('pizzaInputField').required = false;
        document.getElementById('priceInputField').required = true;
        document.getElementById('ingredientsInputField').required = true;
   }
   else if(document.getElementById('deleteselected')){
        document.getElementById('pizzaInput').style.display = 'none';
        document.getElementById('pizzaSelector').style.display = 'block';
        document.getElementById('priceInput').style.display = 'none';
        document.getElementById('ingredientsInput').style.display = 'none';
        document.getElementById('button').style.display = 'block';
        
        //Now, we have to set every div to not required
        document.getElementById('pizzaInputField').required = false;
        document.getElementById('priceInputField').required = false;
        document.getElementById('ingredientsInputField').required = false;
   }
}




