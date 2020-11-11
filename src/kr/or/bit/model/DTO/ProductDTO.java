package kr.or.bit.model.DTO;

public class ProductDTO {
   private int p_num;
   private int sel_num;
   private String p_name;
   private int p_price;
   private char p_size;
   private int p_amount;
   private String p_description;
   
   
   public int getP_num() {
      return p_num;
   }
   public void setP_num(int p_num) {
      this.p_num = p_num;
   }
   public int getSel_num() {
      return sel_num;
   }
   public void setSel_num(int sel_num) {
      this.sel_num = sel_num;
   }
   public String getP_name() {
      return p_name;
   }
   public void setP_name(String p_name) {
      this.p_name = p_name;
   }
   public int getP_price() {
      return p_price;
   }
   public void setP_price(int p_price) {
      this.p_price = p_price;
   }
   public char getP_size() {
      return p_size;
   }
   public void setP_size(char p_size) {
      this.p_size = p_size;
   }
   public int getP_amount() {
      return p_amount;
   }
   public void setP_amount(int p_amount) {
      this.p_amount = p_amount;
   }
   public String getP_description() {
      return p_description;
   }
   public void setP_description(String p_description) {
      this.p_description = p_description;
   }
   @Override
   public String toString() {
      return "ProductDTO [p_num=" + p_num + ", sel_num=" + sel_num + ", p_name=" + p_name + ", p_price=" + p_price
            + ", p_size=" + p_size + ", p_amount=" + p_amount + ", p_description=" + p_description + "]";
   }
   
   
}