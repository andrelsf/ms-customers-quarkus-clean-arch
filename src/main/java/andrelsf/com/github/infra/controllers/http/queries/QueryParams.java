package andrelsf.com.github.infra.controllers.http.queries;

public record QueryParams(Integer page, Integer size) {

  public static QueryParams create(final Integer page, Integer size) {
    return new QueryParams(page, size);
  }
}
