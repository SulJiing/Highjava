function f_fnc() {
    // let result1 = [];
    // while (true) {
    //   let v_keyword = prompt("키워드 입력 : ");
    //   if (v_keyword === "" || v_keyword === null) {
    //     break;
    //   }
    //   result1.push(v_keyword);
    // }
  
    // let sum1 = result1.join(', '); 
  
    // let result2 = document.getElementById("sum1");
    // result2.innerHTML = "모든 키워드: " + sum1;

    let v_arr = [];
    for(;;) {
      let name = prompt("이름입력");
      if(name == "" || name == null) break;

      if(v_arr.indexOf(name) == -1){
        v_arr.push(name); 
      } else {
        alert("중복된 이름입니다.");
      }
    }
    document.querySelector('div').innerHTML = v_arr;
  }

  let f_output = () => {

    let v_arr = [];
    while(1) {
      let su = prompt("수를 입력");

      // Number() - 숫자 또는 NaN반환
      console.log("Number() => ",Number(su));
      // isNaN() - 인수를 number타입으로 형변환하여 숫자 여부를 boolean으로 확인
      console.log("isNaN() => ",isNaN(su));
      
      // 숫자 데이터만 입력가능하도록
      if(isNaN(su)) continue; // 문자정보는 skip(= 아래코드를 실행하지 않음)

      // 공백정보는 입력하지 않도록
      // trim()을 이용해 정보의 앞뒤 공백을 제거
      if(su.trim().length != 0) {
        
        // 중복체크 - indexOf(없는값)일 때 -1반환
        if(v_arr.indexOf(su) == -1){
          // 중복되지 않은 수이므로 배열에 담기
          v_arr.push(su); // 중복되지 않는 수만 담기
        } else {
          alert("중복된 수입니다.");
        }
      }
      // 무한반복을 빠져나갈 조건 => 중복되지 않는 정수 5개
      if(v_arr.length == 5) break;
    }
    document.querySelector('div').innerHTML = v_arr;
  };