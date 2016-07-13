class BooksController < ApplicationController
  def show
  end

  def index
  @books = Book.all
  #raise @books.inspect
  end

  def new
  end

  def create
  end

  def edit
  end

  def update
  end

  def destroy
  end
end
