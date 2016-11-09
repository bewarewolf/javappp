package com.nixsolutions;

public class MyBean {

  @Public
  public String strPublic1 = "Annotated public field";

  public String strPublic2 = "Public field";

  @Public
  protected String strProtected1 = "Annotated protected field";

  protected String strProtected2 = "Protected field";

  @Public
  String strPackage1 = "Annotated package field";

  String strPackage2 = "Package field";

  @Public
  private String strPrivate1 = "Annotated private field";

  private String strPrivate2 = "Private field";

  public MyBean() {
  }

  public MyBean(String strPublic1, String strPublic2, String strProtected1, 
      String strProtected2, String strPackage1, String strPackage2, 
      String strPrivate1, String strPrivate2) {
    this.strPublic1 = strPublic1;
    this.strPublic2 = strPublic2;
    this.strProtected1 = strProtected1;
    this.strProtected2 = strProtected2;
    this.strPackage1 = strPackage1;
    this.strPackage2 = strPackage2;
    this.strPrivate1 = strPrivate1;
    this.strPrivate2 = strPrivate2;
  }
}
