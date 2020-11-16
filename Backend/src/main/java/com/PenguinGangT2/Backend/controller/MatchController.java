package com.PenguinGangT2.Backend.controller;

import com.PenguinGangT2.Backend.exception.ResourceNotFoundException;
import com.PenguinGangT2.Backend.models.Match;
import com.PenguinGangT2.Backend.repository.MatchRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/match")
public class MatchController {

  @Autowired
  private MatchRepository matchRepo;

  @GetMapping
  public Collection<Match> getAll() {
    return matchRepo.findAll();
  }

  @GetMapping(value = "/{id}")
  public Match getMatch(@PathVariable String id) {
    return matchRepo
      .findById(id)
      .orElseThrow(() -> new ResourceNotFoundException());
  }

  @GetMapping(value = "/tournament/{tournamentId}")
  public List<Match> getMatchesByTournamentId(
    @PathVariable String tournamentId
  ) {
    List<Match> matchList = new ArrayList<>();
    matchList = matchRepo.findAll();
    List<Match> matches = new ArrayList<>();

    for (int i = 0; i < matchList.size(); i++) {
      if (matchList.get(i).getTournamentId().equals(tournamentId)) {
        matches.add(matchList.get(i));
      }
    }
    return matches;
  }

  @PostMapping
  public Match postMatch(@Valid @RequestBody Match match) {
    return matchRepo.save(match);
  }

  @PutMapping(value = "/{id}")
  public Match updateMatch(@RequestBody Match match, @PathVariable String id) {
    Optional<Match> original = matchRepo.findById(id);

    if (!original.isPresent()) {
      return matchRepo
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException());
    }
    System.out.println(original);

    match.setId(id);

    return matchRepo.save(match);
  }

  @DeleteMapping(value = "/{id}")
  public void deleteMatch(@PathVariable String id) {
    matchRepo.deleteById(id);
  }
}
