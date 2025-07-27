package com.alura.literalura.DTO;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
