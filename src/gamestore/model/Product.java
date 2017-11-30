// Class that represents a record in the Products table.
package gamestore.model;

public class Product {
    private int productID;
    private String title;
    private double price;
    private String description;
    private String video;
    private String platform;
    private String cover;
    private String image1;
    private String image2;
    private String image3;
    private String image4;
    private String exeFile;
    
    // Default constructor
    public Product(){}
    
    // Constructor
    public Product(int productID, String title, double price, String description, String video, String platform, String cover, String image1, String image2, String image3, String image4, String exeFile) {
        setProductID(productID);
        setTitle(title);
        setPrice(price);
        setDescription(description);
        setVideo(video);
        setPlatform(platform);
        setCover(cover);
        setImage1(image1);
        setImage2(image2);
        setImage3(image3);
        setImage4(image4);
        setExeFile(exeFile);
    }
    
    // Set and get methods
    public void setProductID(int productID){
        this.productID = productID;
    }
    
    public int getProductID(){
        return productID;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getTitle(){
        return title;
    }
    
    public void setPrice(double price) {
        this.price = price;
    } 
    
    public double getPrice() {
        return price;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setVideo(String video) {
        this.video = video;
    }
    
    public String getVideo(){
        return video;
    }
    
    public void setPlatform(String platform) {
        this.platform = platform;
    }
    
    public String getPlatform() {
        return platform;
    }
    
    public void setCover(String cover) {
        this.cover = cover;
    }
    
    public String getCover() {
        return cover;
    }
    
    public void setImage1(String image1) {
        this.image1 = image1;
    }
    
    public String getImage1() {
        return image1;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }
    
    public String getImage2() {
        return image2;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }
    
    public String getImage3() {
        return image3;
    }    
    
    public void setImage4(String image4) {
        this.image4 = image4;
    }
    
    public String getImage4() {
        return image4;
    }    
    
    public void setExeFile(String exeFile) {
        this.exeFile = exeFile;
    }
    
    public String getExeFile() {
        return exeFile;
    }
}