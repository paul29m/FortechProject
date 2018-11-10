package ro.fortech.winewiki.apigateway.dto;

public class ApiGatewayWineTypeDto extends ApiGatewayNewWineTypeDto {
    private Long id;
    private ApiGatewayWineDtoList wines;

    private ApiGatewayWineTypeDto() {
    }

    public ApiGatewayWineTypeDto(Long id,
                                 String name,
                                 ApiGatewayWineDtoList wines) {
        super(name);
        this.id = id;
        this.wines = wines;
    }

    public Long getId() {
        return id;
    }

    public ApiGatewayWineDtoList getWines() {
        return wines;
    }

    @Override
    public String toString() {
        return "ApiGatewayWineTypeDto{" +
                "id=" + id +
                ", wines=" + wines +
                '}';
    }
}

