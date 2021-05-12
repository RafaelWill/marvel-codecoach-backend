package be.marvel.code.coach.infrastructure.util;

public interface Mapper<Tdto, Tentity> {
    Tdto toDto(Tentity entity);

    Tentity toEntity(Tdto dto);
}
