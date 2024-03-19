package in.madhu.Bindings;

public class ResetPwdForm {

	private Integer UserId;
	private String Email;
	private String newPwd;
	private String ConfirmPwd;
	public Integer getUserId() {
		return UserId;
	}
	public void setUserId(Integer userId) {
		UserId = userId;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getNewPwd() {
		return newPwd;
	}
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	public String getConfirmPwd() {
		return ConfirmPwd;
	}
	public void setConfirmPwd(String confirmPwd) {
		ConfirmPwd = confirmPwd;
	}
	
}
