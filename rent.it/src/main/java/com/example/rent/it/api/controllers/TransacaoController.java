package com.example.rent.it.api.controllers;

import com.example.rent.it.csv.ListaAluguel;
import com.example.rent.it.object.transacao.Transacao;
import com.example.rent.it.object.usuario.Usuario;
import com.example.rent.it.ordenacao.ListaObj;
import com.example.rent.it.repository.UsuarioRepository;
import com.example.rent.it.service.TransacaoService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;



  @GetMapping("/csv/{id}")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Arquivo enviado com sucesso"),
          @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
  })
    public ResponseEntity<byte[]> getListaAluguel(@PathVariable Long id){
          try {
              InputStream fileInputStream = new FileInputStream(this.transacaoService.getAlugueis(id));
              return ResponseEntity.status(200)
                      .header("Content-Disposition",
                              "attachment; filename=" + LocalDate.now() + ".csv")
                      .body(fileInputStream.readAllBytes());

             } catch(Exception e){
              e.printStackTrace();
          }


     return null;
  }
    @GetMapping("/csv/ordenado/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Arquivo enviado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
             })
    public ResponseEntity<byte[]> getListaAluguelOrdenado(@PathVariable Long id){
        try {
            InputStream fileInputStream = new FileInputStream(this.transacaoService.getAlugueisOrdenado(id));
            return ResponseEntity.status(200)
                    .header("Content-Disposition",
                            "attachment; filename=" + LocalDate.now() + ".csv")
                    .body(fileInputStream.readAllBytes());

        } catch(Exception e){
            e.printStackTrace();
        }


        return null;
    }
  @GetMapping
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Arquivo enviado com sucesso"),
          @ApiResponse(responseCode = "500", description = "Serviço não disponivel")
  })
    public List<Transacao> getTransacoes(){
      return this.transacaoService.getTransacoes();
  }
    @GetMapping("/precoalugado/{id}/{preco}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Arquivo enviado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
    })
    public Optional<Transacao> getTransacaoPorPreco(@PathVariable int preco,
                                                    @PathVariable Long id){

        return this.transacaoService.getTransacaoPorPreco(preco, id);
    }












}
