package com.RestAssuredTestNG.APITests.APIConfigs.PojoClasses;

public class RegisterPOJO {
    private String name;
    private String email;
    private String password;

    public RegisterPOJO(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

   public String getName(){
        return name;
   }

   public void SetName(String name){
        this.name=name;
   }

    public String getEmail(){
          return email;
    }

    public void SetEmail(String email){
        this.email=email;
    }

    public String getPassword(){
        return password;
    }

    public void SetPassword(String password){
        this.password=password;
    }



}
