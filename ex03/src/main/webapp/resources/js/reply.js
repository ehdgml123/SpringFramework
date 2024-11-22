console.log("Reply Module....")

let replyService = (function(){


    function add(reply, callback, error){
        console.log("reply...........");
    

    $.ajax({
         type : "post",  // post 방식으로 
         url : "/replies/new",  //데이터 보낼 경로
         data : JSON.stringify(reply),  // json 으로 반환
         contentType : "application/json; charset=utf-8",  // json 타입으로, ust-8
         success: function(result, status, xhr){
            if(callback){
                callback(result);   
                // 요청이 성공했을 때 실행되는 함수. 
                //콜백 함수가 정의된 경우 그 함수를 호출하여 결과(result)를 전달
            }
         },

         error: function(xhr, status, er){
            if(error){
                error(er);
            }
         }
    })
} // add end

 // start getList
 function getList(param, callback, error){
      
    let bno = param.bno;

    let page = param.page || 1;

    $.ajax({
        type: "get",
        url : "/replies/pages/" + bno + "/" + page,
        success: function(result, status, xhr){
            if(callback){
                callback(result);
            }
        },
        error : function(xhr, status, er){
            if(error){
                error(er);
            }
        }
    });
 }

 // end getList

 // remove start
 function remove(rno, callback, error){
    
    $.ajax({
        type : "delete",
        url : "/replies/" + rno,
        success : function(deleteResult, status, xhr){
             if(callback){
                callback(deleteResult)
             }
        },
        
        error: function(xhr, status, er){
               if(error){
                error(er);
               }
        }
    })
 }
 // remove end

 // update start

   function update(reply, callback, error){
        
    $.ajax({
       type : "put",
       url : "/replies/" + reply.rno,
       data : JSON.stringify(reply),
       contentType : "application/json; charset=utf-8",
       success: function(result, status, chr){
        if(callback){
            callback(result);
        }
       },
       error : function(xhr, status, er){
        if(error){
            error(er);
        }
       }
    });
   }

 // update end

 // get start

 function get(rno, callback, error){
     
    $.ajax({
        type : "get",
        url : "/replies/" + rno,
        success : function(replyVO,status, xhr){
            if(callback){
                callback(replyVO);
            }
        },

        error: function(status, xhr, er){
             if(error){
                error(er);
             }
        }
    })
 }
// get end

// displyTime start
function displyTime(timevalue){
   
    let today = new Date();

    let gap = today.getTime() - timevalue;

    let dateObj = new Date(timevalue);
    let str = "";

    if(gap < (1000* 60*60 *24)){
        let hh = dateObj.getHours();
        let mi = dateObj.getMinutes();
        let ss = dateObj.getSeconds();

        return [ (hh>9 ? '' : '0') +hh, ':', (mi>9? '' : '0') + mi, ':', (ss >9? '' : '0')+ss].join('');
       
    }else{
        let yy = dateObj.getFullYear();
        let mm = dateObj.getMonth()+1;  //1월 : 0 , 2월 : 2  => 1월 : 0+1
        let dd = dateObj.getDate();

        return [ yy, '/', (mm > 9 ? '' : '0') + mm, '/', (dd >9 ? '' : '0') +dd].join('');
    }


}

//displyTime end;

    return{
        add :add,
        getList : getList,
        remove : remove,
        update : update,
        get : get,
        displyTime : displyTime
    };

})();