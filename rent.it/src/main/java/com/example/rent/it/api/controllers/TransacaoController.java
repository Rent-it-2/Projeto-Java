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

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;



  @GetMapping("/download/{id}")
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

  @GetMapping
    public List<Transacao> getTransacoes(){
      return this.transacaoService.getTransacoes();
  }

}
