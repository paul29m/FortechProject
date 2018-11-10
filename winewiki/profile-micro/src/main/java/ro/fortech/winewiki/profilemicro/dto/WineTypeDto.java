package ro.fortech.winewiki.profilemicro.dto;

public class WineTypeDto extends NewWineTypeDto {
    private Long id;
    private WineDtoList wines;

    private WineTypeDto() {
    }

    public WineTypeDto(Long id,
                       String name,
                       WineDtoList wines) {
        super(name);
        this.id = id;
        this.wines = wines;
    }
    public Long getId() {
        return id;
    }

    public WineDtoList getWines() {
        return wines;
    }

    @Override
    public String toString() {
        return "WineTypeDto{" +
                "id=" + id +
                ", wines=" + wines +
                '}';
    }
}

