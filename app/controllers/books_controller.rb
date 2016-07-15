class BooksController < ApplicationController
  def show
  end

  # to get index of books
  def index
  @books = Book.all
  end

  def new
	@book = Book.new
  end

  def create
    @book = Book.new(student_params)

    respond_to do |format|
      if @book.save
        format.html { redirect_to @book, notice: 'Book was successfully created.' }
        format.json { render :show, status: :created, location: @book }
      else
        format.html { render :new }
        format.json { render json: @book.errors, status: :unprocessable_entity }
      end
    end
  end

  def edit
  end

  def update
  end

  def destroy
  end
  
    private

    def set_student
      @book = Book.find(params[:id])
    end

    def book_params
      params.require(:book).permit(:Title, :Author, :Price)
    end
end
