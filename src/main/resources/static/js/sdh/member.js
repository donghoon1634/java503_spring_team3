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
