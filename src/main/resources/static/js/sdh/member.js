const signUpButton = document.getElementById('signUp')
const signInButton = document.getElementById('signIn')
const container = document.getElementById('container')
const signUpForm = document.querySelector('.sign-up-container form') // Sign-up form
const signInForm = document.querySelector('.sign-in-container form') // Sign-in form

signUpButton.addEventListener('click', () => {
  container.classList.add('right-panel-active')
  resetForm(signUpForm) // Sign-up form 리셋
})

signInButton.addEventListener('click', () => {
  container.classList.remove('right-panel-active')
  resetForm(signInForm) // Sign-in form 리셋
})

// 폼 리셋 함수
function resetForm (form)
{
  const inputs = form.querySelectorAll('input')
  inputs.forEach(input => {
    input.value = '' // 각 입력 필드의 값을 초기화
  })
}

// 주소 선택 완료 확인 함수
function checkAddressSelection ()
{
  const gu = document.getElementById('loadAddrGu').value
  const dong = document.getElementById('loadAddrDong').value
  const ro = document.getElementById('loadAddrRo').value
  const mainNum = document.getElementById('loadAddrMainNum').value
  const subNumElement = document.getElementById('loadAddrSubNum')
  const subNum = subNumElement.style.display !== 'none' ? subNumElement.value : '' // subNum이 보이는 경우에만 값을 사용
  
  // 주소가 모두 선택되지 않은 경우
  if (!gu || !dong || !ro || !mainNum || (subNumElement.style.display !== 'none' && !subNum))
  {
    alert('주소를 모두 선택해주세요.')
    return false // 기본 폼 제출 방지
  }
  
  // 주소 정보 서버로 요청해서 최종 주소를 받아옴
  let url = `/test/getAddr?gu=${gu}&dong=${dong}&ro=${ro}&mainNum=${mainNum}`
  if (subNum)
  {
    url = `/test/getAddrWithSubNum?gu=${gu}&dong=${dong}&ro=${ro}&mainNum=${mainNum}&subNum=${subNum}`
  }
  
  // AJAX 요청을 통해 서버에서 주소 받아오기
  fetch(url)
    .then(response => response.text())  // 서버로부터 받은 주소를 텍스트로 처리
    .then(data => {
      if (!data || data.trim() === '')
      {
        // 응답이 빈 값이거나 null일 경우 error 처리
        throw new Error('주소 조회 실패: 응답이 비어있습니다.')
      }
      
      // 서버에서 받은 주소를 memberAddr에 저장
      document.getElementById('memberAddr').value = data  // 받은 주소를 hidden 필드에 설정
      
      // 최종 주소가 제대로 설정되었는지 확인
      const memberAddr = document.getElementById('memberAddr').value
      if (!memberAddr)
      {
        alert('주소 조회 실패, 다시 시도해주세요.')
        return false
      }
      
      // 폼을 비동기적으로 제출
      submitForm()  // 주소 조회 후 폼 제출
    })
    .catch(error => {
      console.error(error)  // 오류 메시지를 콘솔에 출력
      alert('주소 조회 실패, 다시 시도해주세요.')  // 사용자에게 알림
    })
  
  return false // 기본 폼 제출 방지 (비동기 처리를 위해)
}

// 폼 제출 함수
function submitForm ()
{
  // 폼을 비동기적으로 제출
  document.forms[0].submit()  // 폼 제출
}




