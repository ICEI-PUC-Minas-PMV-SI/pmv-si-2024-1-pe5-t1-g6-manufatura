package com.ecocleaning.webstore.products;


public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private Integer price;
    private Integer cents = 0;

    public ProductDto() {
    }

    private void validate() {
        if (this.price == null  || this.price < 0) {
            this.price = 0;
        }
        if (this.cents == null || this.cents < 0) {
            this.cents = 0;
        }

        if(this.cents > 99) {
            this.cents = 99;
        }

        if (this.name == null || this.name.isEmpty()) {
            throw new IllegalArgumentException("O nome do produto nao pode ser vazio");
        }
        if (this.imageUrl == null || this.imageUrl.isEmpty()) {
            throw new IllegalArgumentException("URL da imagem é obrigatória");
        }

    }

    public Product toProduct() {
        this.validate();
        System.out.println(this);
        final var product = new Product();
        product.setId(this.id);
        product.setName(this.name);
        product.setDescription(this.description);
        product.setImageUrl(this.imageUrl);
        final var centsTwoDigits = Integer.parseInt(String.format("%02d", this.cents));
        final var concatPrice = this.price + ((double) centsTwoDigits / 100);
        product.setPrice(concatPrice);
        return product;
    }

    @Override
    public String toString() {
        return "ProductPutDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", price=" + price +
                ", cents=" + cents +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCents() {
        return cents;
    }

    public void setCents(Integer cents) {
        this.cents = cents;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
