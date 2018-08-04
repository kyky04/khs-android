package id.stimik.khs.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class MahasiswaResponse{

	@SerializedName("data")
	private List<ItemMahasiswa> data;

	@SerializedName("success")
	private boolean success;

	@SerializedName("message")
	private String message;

	public void setData(List<ItemMahasiswa> data){
		this.data = data;
	}

	public List<ItemMahasiswa> getData(){
		return data;
	}

	public void setSuccess(boolean success){
		this.success = success;
	}

	public boolean isSuccess(){
		return success;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"MahasiswaResponse{" + 
			"data = '" + data + '\'' + 
			",success = '" + success + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}